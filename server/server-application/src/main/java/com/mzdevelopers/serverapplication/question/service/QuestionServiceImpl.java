package com.mzdevelopers.serverapplication.question.service;

import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.mapper.AnswerMapper;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.answervote.repository.AnswerVoteRepository;
import com.mzdevelopers.serverapplication.comment.dto.CommentDto;
import com.mzdevelopers.serverapplication.comment.entity.Comment;
import com.mzdevelopers.serverapplication.comment.mapper.CommentMapper;
import com.mzdevelopers.serverapplication.exception.BusinessLogicException;
import com.mzdevelopers.serverapplication.exception.ExceptionCode;
import com.mzdevelopers.serverapplication.member.dto.MemberInfoDto;
import com.mzdevelopers.serverapplication.member.dto.MemberResponseDto;
import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
import com.mzdevelopers.serverapplication.question.dto.QuestionPatchRequestDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionResponseDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionVoteCountDto;
import com.mzdevelopers.serverapplication.question.mapper.QuestionMapper;
import com.mzdevelopers.serverapplication.question.stub.StubAnswer;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.entity.QuestionVote;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
import com.mzdevelopers.serverapplication.question.repository.QuestionVoteRepository;
import com.mzdevelopers.serverapplication.tag.dto.SelectTagDto;
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
    private final CommentMapper commentMapper;
    private final AnswerVoteRepository answerVoteRepository;

    @Override
    public long createQuestion(Question question, List<TagNameDto> tags) {


        Member findMember = memberRepository.findById(question.getMember().getMemberId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));



        question.setMember(findMember);
        Question savedQuestion = questionRepository.save(question);


        if (!tags.isEmpty()) {
            List<Tag> tagList = findByTagId(tags);
            List<QuestionTag> questionTags = new ArrayList<>();
            for (Tag tag : tagList) {
                QuestionTag questionTag = new QuestionTag(savedQuestion, tag);
                questionTags.add(questionTag);
            }
            questionTagRepository.saveAll(questionTags);
//            question.setQuestionTags(questionTags);
        }


        return savedQuestion.getQuestionId();
    }

    @Override
    public QuestionResponseDto getQuestion(long questionId, long memberId, String flag) {
        Question findQuestion = findByQuestionId(questionId);
        Member findMember = findByMemberId(memberId);
        QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(findQuestion);
        if(isRegisteredMember(memberId) && !flag.equals("api")){
            findQuestion.increaseView();
            questionRepository.save(findQuestion);
        }
        Optional<QuestionVote> questionVote = questionVoteRepository.findByQuestionAndMember(findQuestion, findMember);
        if(questionVote.isEmpty())
            responseDto.setQuestionVoteByMember(false);
        else responseDto.setQuestionVoteByMember(questionVote.get().isQuestionVoted());

        List<AnswerDto.Response> answers = new ArrayList<>();
        getQuestionAnswer(findQuestion, findMember, answers);

        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .memberId(findQuestion.getMember().getMemberId())
                .userName(findQuestion.getMember().getName())
                .picture(findQuestion.getMember().getPicture())
                .build();
        responseDto.setMemberInfoDto(memberInfoDto);
        responseDto.setAnswers(answers);
        responseDto.setTags(findByQuestionTag(findQuestion));
        return responseDto;
    }

    private void getQuestionAnswer(Question findQuestion, Member findMember, List<AnswerDto.Response> answers) {
        for (Answer answer : findQuestion.getAnswers()) {
            AnswerDto.Response response = answerMapper
                    .answerToAnswerResponse(answer);
            Optional<AnswerVote> answerVote = answerVoteRepository.findByAnswerAndMember(answer, findMember);
            if(answerVote.isEmpty())
                response.setAnswerVoteByMember(false);
            else response.setAnswerVoteByMember(answerVote.get().isAnswerVoted());
            MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                    .memberId(answer.getMember().getMemberId())
                    .userName(answer.getMember().getName())
                    .picture(answer.getMember().getPicture())
                    .build();
            response.setMemberInfoDto(memberInfoDto);
            List<CommentDto.Response> comments= new ArrayList<>();
            for(Comment comment : answer.getComments()){
                CommentDto.Response commentResponse = commentMapper
                        .commentToCommentResponse(comment);
                MemberInfoDto memberCommentInfoDto = MemberInfoDto.builder()
                        .memberId(comment.getMember().getMemberId())
                        .userName(comment.getMember().getName())
                        .picture(comment.getMember().getPicture())
                        .build();

                commentResponse.setMemberInfoDto(memberCommentInfoDto);
                comments.add(commentResponse);
            }
            response.setComments(comments);
            answers.add(response);
        }
    }

    public QuestionPatchRequestDto getPatchQuestion(long questionId, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        if(findQuestion.getMember().getMemberId() != memberId)
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_QUESTION);

        List<SelectTagDto> selectTagDtoList = new ArrayList<>();
        List<Tag> tagList = tagRepository.findAll();
        for(Tag tag : tagList){
            SelectTagDto selectTagDto = new SelectTagDto();
            selectTagDto.setSelect(false);
            selectTagDto.setTagName(tag.getTagName());
            selectTagDtoList.add(selectTagDto);
        }
        for(QuestionTag questionTag : findQuestion.getQuestionTags()){
            for(SelectTagDto selectTagDto : selectTagDtoList){
                if(questionTag.getTag().getTagName().equals(selectTagDto.getTagName())){
                    selectTagDto.setSelect(true);
                }
            }
        }

        QuestionPatchRequestDto questionPatchRequestDto = questionMapper.questionToQuestionPatchRequestDto(findQuestion);
        questionPatchRequestDto.setTags(selectTagDtoList);
        return questionPatchRequestDto;
    }


    @Override
    public Question updateQuestion(long questionId, String title, String detail,List<TagNameDto> tags, long memberId) {

        Question findQuestion = findByQuestionId(questionId);
        if (findQuestion.getMember().getMemberId() == memberId) {
            findQuestion.update(title, detail);


            if (!tags.isEmpty()) {
                List<QuestionTag> findQuestionTags = findQuestion.getQuestionTags();
                for(QuestionTag findQuestionTag:findQuestionTags){
                    questionTagRepository.delete(findQuestionTag);
                }

                List<Tag> tagList = findByTagId(tags);
                List<QuestionTag> questionTags = new ArrayList<>();
                for (Tag tag : tagList) {
                    QuestionTag questionTag = new QuestionTag(findQuestion, tag);
                    questionTags.add(questionTag);
                }

                questionTagRepository.saveAll(questionTags);
                findQuestion.setQuestionTags(questionTags);
            }

            return questionRepository.save(findQuestion);

        } else {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_QUESTION);
        }
    }

    @Override
    public void deleteQuestion(long questionId, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        if(findQuestion.getMember().getMemberId() == memberId)
            questionRepository.delete(findQuestion);
        else
            throw new BusinessLogicException(ExceptionCode.CANNOT_DELETE_QUESTION);
    }

    // -------------------------------------------------------------- 질문 CRUD

    @Override
    public QuestionVoteCountDto votesCount(long questionId, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        Member findMember = findByMemberId(memberId);
        Optional<QuestionVote> optionalQuestionVote = questionVoteRepository.findByQuestionAndMember(findQuestion, findMember);
        QuestionVote saveQuestion;
        if (optionalQuestionVote.isEmpty()) {
            QuestionVote questionVote = QuestionVote.builder().question(findQuestion).member(findMember).build();
            findQuestion.updateVoteCount(true);
            saveQuestion = questionVoteRepository.saveAndFlush(questionVote);
        } else {
            QuestionVote findQuestionVote = optionalQuestionVote.get();
            findQuestionVote.updateVote();
            saveQuestion = questionVoteRepository.saveAndFlush(findQuestionVote);
            findQuestion.updateVoteCount(findQuestionVote.isQuestionVoted());
        }
        Question updatedQuestion = questionRepository.saveAndFlush(findQuestion);
        QuestionVoteCountDto questionVoteCountDto = new QuestionVoteCountDto();
        questionVoteCountDto.setTotalVoteCount(updatedQuestion.getVotesCount());
        questionVoteCountDto.setQuestionVoteStatus(saveQuestion.isQuestionVoted());
        return questionVoteCountDto;
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
        return findQuestion.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    public Member findByMemberId(Long memberId){
        Optional<Member> findMember = memberRepository.findById(memberId);
        return findMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
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
