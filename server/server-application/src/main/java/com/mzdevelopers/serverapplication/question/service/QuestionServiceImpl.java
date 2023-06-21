package com.mzdevelopers.serverapplication.question.service;

import com.mzdevelopers.serverapplication.question.dto.QuestionResponseDto;
import com.mzdevelopers.serverapplication.question.mapper.QuestionMapper;
import com.mzdevelopers.serverapplication.question.stub.MemberStub;
import com.mzdevelopers.serverapplication.question.stub.StubAnswer;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.entity.QuestionVote;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
import com.mzdevelopers.serverapplication.question.repository.QuestionVoteRepository;
import com.mzdevelopers.serverapplication.question.stub.MemberStubRepository;
import com.mzdevelopers.serverapplication.tag.dto.TagDto;
import com.mzdevelopers.serverapplication.tag.entity.QuestionTag;
import com.mzdevelopers.serverapplication.tag.entity.Tag;
import com.mzdevelopers.serverapplication.tag.repository.QuestionTagRepository;
import com.mzdevelopers.serverapplication.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;
    private final QuestionTagRepository questionTagRepository;
    private final TagRepository tagRepository;
    private final QuestionVoteRepository questionVoteRepository;
    private final MemberStubRepository memberStubRepository;
    private final QuestionMapper questionMapper;

    @Override
    public long createQuestion(Question question, List<Long> tags) {
        Question savedQuestion = questionRepository.save(question);

        if (!tags.isEmpty()) {
            List<Tag> tagList = findByTagId(tags);
            List<QuestionTag> questionTags = new ArrayList<>();
            for (Tag tag : tagList) {
                QuestionTag questionTag = new QuestionTag(savedQuestion, tag);
                questionTags.add(questionTag);
            }
            questionTagRepository.saveAll(questionTags);
        }

        return savedQuestion.getQuestionId();
    }

    @Override
    public QuestionResponseDto getQuestion(long questionId, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(findQuestion);
        save(); // stub -> delete
        if(isRegisteredMember(memberId)){
            findQuestion.increaseView();
            questionRepository.save(findQuestion);
        }
        responseDto.setAnswers(findQuestion.getAnswers());
        responseDto.setTags(findByQuestionTag(findQuestion));
        return responseDto;
    }

    @Override
    public Question updateQuestion(long questionId, String title, String detail, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        if (findQuestion.getMemberId() == memberId) {
            findQuestion.update(title, detail);
            return questionRepository.save(findQuestion);
        } else {
            throw new IllegalArgumentException("수정할 권한이 없습니다: " + memberId);
        }
    }

    @Override
    public void deleteQuestion(long questionId, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        if(findQuestion.getMemberId() == memberId)
            questionRepository.delete(findQuestion);
        else
            throw new IllegalArgumentException("삭제할 권한이 없습니다: " + memberId);
    }

    // -------------------------------------------------------------- 질문 CRUD

    @Override
    public int votesCount(long questionId, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        MemberStub memberStub = save();
        Optional<QuestionVote> optionalQuestionVote = questionVoteRepository.findByQuestionAndMemberStub(findQuestion, memberStub);
        if (optionalQuestionVote.isEmpty()) {
            QuestionVote questionVote = QuestionVote.builder().question(findQuestion).memberStub(memberStub).build();
            findQuestion.updateVoteCount(true);
            questionVoteRepository.save(questionVote);
        } else {
            QuestionVote findQuestionVote = optionalQuestionVote.get();
            findQuestionVote.updateVote();
            questionVoteRepository.save(findQuestionVote);
            findQuestion.updateVoteCount(findQuestionVote.isQuestionVoted());
        }
        Question updatedQuestion = questionRepository.save(findQuestion);
        return updatedQuestion.getVotesCount();
    }
    // ------------------------------------------------------------- 종아요 증가 or 감소

    @Override
    public List<Question> questionsListByAPI(int page, int size, String api) {
        Pageable pageable = createPageable(page, size, "createdAt");
        if (api.equals("votes")) {
            pageable = createPageable(page, size, "votesCount");
            return questionRepository.findAllByOrderByVotesCountDesc(pageable).getContent();
        } else if(api.equals("solutions")) {
            return questionRepository.findBySolutionStatusTrueOrderByCreatedAtDesc(pageable).getContent();
        }else {
            return questionRepository.findAll(pageable).getContent();
        }
    }

    public Pageable createPageable(int page, int size, String property) {
        return PageRequest.of(page, size, Sort.by(property).descending());
    }

    //--------------------------------------------------------------- vote, view, answer

    public boolean isRegisteredMember(long memberId) {
        return memberStubRepository.findById(memberId).isPresent();
    }
    public Question findByQuestionId(Long questionId) {
        Optional<Question> findQuestion = questionRepository.findById(questionId);
        return findQuestion.orElseThrow(() -> new IllegalArgumentException("No Search Question: " + questionId));
    }

    public List<Tag> findByTagId(List<Long> tagIds) {
        List<Tag> tagList = new ArrayList<>();
        for (Long id : tagIds) {
            Tag tag = tagRepository.findById(id).orElseGet(() -> null);
            tagList.add(tag);
        }
        return tagList;
    }

    public List<TagDto> findByQuestionTag(Question question) {
        List<TagDto> tagList = new ArrayList<>();
        for (QuestionTag questionTag : question.getQuestionTags()) {
            TagDto tagDto = new TagDto();
            tagDto.setTagName(questionTag.getTag().getTagName());
            tagDto.setTagDescription(questionTag.getTag().getTagDescription());
            tagList.add(tagDto);
        }

        return tagList;
    }

    public URI uriBuilder(long questionId, String basicURL) {
        return UriComponentsBuilder
                .fromUriString(basicURL)
                .path("/"+questionId)
                .build()
                .toUri();
    }
    // --------------------------------------------------------------- 부기능


    // stub data zone
    public List<StubAnswer> stubAnswerList() {
        StubAnswer stubAnswer = new StubAnswer();
        stubAnswer.setAnswerTitle("title");
        stubAnswer.setAnswerDetail("detail");
        stubAnswer.setAnswerId(1L);
        return List.of(stubAnswer);
    }

    public MemberStub save(){
        MemberStub memberStub = MemberStub.builder().name("member").build();
        memberStubRepository.save(memberStub);
        return memberStubRepository.findById(1L).orElseGet(null);
    }



}
