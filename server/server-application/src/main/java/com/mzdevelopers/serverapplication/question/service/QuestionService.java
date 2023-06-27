package com.mzdevelopers.serverapplication.question.service;

import com.mzdevelopers.serverapplication.question.dto.QuestionPatchRequestDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionResponseDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionVoteCountDto;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.tag.dto.SelectTagDto;
import com.mzdevelopers.serverapplication.tag.dto.TagNameDto;
import com.mzdevelopers.serverapplication.tag.entity.QuestionTag;
import com.mzdevelopers.serverapplication.tag.entity.Tag;

import java.util.List;

public interface QuestionService {

    /*
    1. 질문 등록
    2. 질문 검색
    3. 질문 수정
    4. 질문 삭제
    ---
    5. 최신, 좋아요, solutionStatus = true(시간) 질문 리스트
    6. 질문 votes, answers, views 기능
     */
    public long createQuestion(Question question, List<TagNameDto> tags);

    public QuestionResponseDto getQuestion(long questionId, long memberId, String flag);

    public QuestionPatchRequestDto getPatchQuestion(long questionId, long memberId);

    // List, Page 형식으로 클라이언트에게 보냄

    public Question updateQuestion(long questionId, String title, String detail,List<TagNameDto> tags, long memberId);

    public void deleteQuestion(long questionId, long memberId);

    public QuestionVoteCountDto votesCount(long questionId, long memberId);

    public List<Question> questionsListByAPI(int page, int size, String api);



}
