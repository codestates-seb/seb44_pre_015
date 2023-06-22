package com.mzdevelopers.serverapplication.question.service;

import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.mapper.AnswerMapper;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.answervote.repository.AnswerVoteRepository;
import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
import com.mzdevelopers.serverapplication.question.dto.QuestionResponseDto;
import com.mzdevelopers.serverapplication.question.mapper.QuestionMapper;
import com.mzdevelopers.serverapplication.question.stub.StubAnswer;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.entity.QuestionVote;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
import com.mzdevelopers.serverapplication.question.repository.QuestionVoteRepository;
import com.mzdevelopers.serverapplication.tag.dto.TagDto;
import com.mzdevelopers.serverapplication.tag.dto.TagNameDto;
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
    private final MemberRepository memberRepository;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final AnswerVoteRepository answerVoteRepository;

    @Override
    public long createQuestion(Question question, List<TagNameDto> tags) {
        System.out.println("question,service");

        Optional<Member> findMember = memberRepository.findById(question.getMember().getMemberId());//.orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다."))

        System.out.println("findMember,service");

        question.setMember(findMember.get());
        Question savedQuestion = questionRepository.save(question);

        System.out.println("findMember,service, complete");

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
        Member findMember = findByMemberId(memberId);
        QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(findQuestion);
        if(isRegisteredMember(memberId)){
            findQuestion.increaseView();
            questionRepository.save(findQuestion);
        }
        Optional<QuestionVote> questionVote = questionVoteRepository.findByQuestionAndMember(findQuestion, findMember);
        if(questionVote.isEmpty())
            responseDto.setQuestionVoteByMember(false);
        else responseDto.setQuestionVoteByMember(questionVote.get().isQuestionVoted());

        List<AnswerDto.Response> answers = new ArrayList<>();
        for (Answer answer : findQuestion.getAnswers()) {
            AnswerDto.Response response = answerMapper.answerToAnswerResponse(answer);
            Optional<AnswerVote> answerVote = answerVoteRepository.findByAnswerAndMember(answer, findMember);
            if(answerVote.isEmpty())
                response.setAnswerVoteByMember(false);
            else response.setAnswerVoteByMember(answerVote.get().isAnswerVoted());
            answers.add(response);
        }

        responseDto.setAnswers(answers);
        responseDto.setTags(findByQuestionTag(findQuestion));
        return responseDto;
    }

    @Override
    public Question updateQuestion(long questionId, String title, String detail, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        if (findQuestion.getMember().getMemberId() == memberId) {
            findQuestion.update(title, detail);
            return questionRepository.save(findQuestion);
        } else {
            throw new IllegalArgumentException("수정할 권한이 없습니다: " + memberId);
        }
    }

    @Override
    public void deleteQuestion(long questionId, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        if(findQuestion.getMember().getMemberId() == memberId)
            questionRepository.delete(findQuestion);
        else
            throw new IllegalArgumentException("삭제할 권한이 없습니다: " + memberId);
    }

    // -------------------------------------------------------------- 질문 CRUD

    @Override
    public int votesCount(long questionId, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        Member findMember = findByMemberId(memberId);
        Optional<QuestionVote> optionalQuestionVote = questionVoteRepository.findByQuestionAndMember(findQuestion, findMember);

        if (optionalQuestionVote.isEmpty()) {
            QuestionVote questionVote = QuestionVote.builder().question(findQuestion).member(findMember).build();
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
        return memberRepository.findById(memberId).isPresent();
    }
    public Question findByQuestionId(Long questionId) {
        Optional<Question> findQuestion = questionRepository.findById(questionId);
        return findQuestion.orElseThrow(() -> new IllegalArgumentException("No Search Question: " + questionId));
    }

    public Member findByMemberId(Long memberId){
        Optional<Member> findMember = memberRepository.findById(memberId);
        return findMember.orElseThrow(() -> new IllegalArgumentException("No Search Member: " + memberId));
    }

    public List<Tag> findByTagId(List<TagNameDto> tagNameDtoList) {
        List<Tag> tagList = new ArrayList<>();
        for (TagNameDto tagNameDto : tagNameDtoList) {
            Tag tag = tagRepository.findByTagName(tagNameDto.getTagName());
            tagList.add(tag);
        }
        return tagList;
    }

    public List<TagNameDto> findByQuestionTag(Question question) {
        List<TagNameDto> tagList = new ArrayList<>();
        for (QuestionTag questionTag : question.getQuestionTags()) {
            TagNameDto tagDto = new TagNameDto();
            tagDto.setTagName(questionTag.getTag().getTagName());
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

    public List<TagNameDto> getTags() {
        List<Tag> tagList = tagRepository.findAll();
        List<TagNameDto> tagNameDtoList = new ArrayList<>();
        for (Tag tag : tagList) {
            TagNameDto tagNameDto = new TagNameDto();
            tagNameDto.setTagName(tag.getTagName());
            tagNameDtoList.add(tagNameDto);
        }
        return tagNameDtoList;
    }

}
