package com.mzdevelopers.serverapplication.member.service;

import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    public MemberService(MemberRepository memberRepository, QuestionRepository questionRepository) {
        this.memberRepository = memberRepository;
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions(long memberId){

        Member member = memberRepository.findByMemberId(memberId);
        return questionRepository.findByMember(member);
    }

}
