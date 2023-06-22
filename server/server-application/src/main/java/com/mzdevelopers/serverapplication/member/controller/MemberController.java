package com.mzdevelopers.serverapplication.member.controller;

import com.mzdevelopers.serverapplication.member.dto.MemberResponseDto;
import com.mzdevelopers.serverapplication.member.mapper.MemberMapper;
import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
import com.mzdevelopers.serverapplication.member.service.MemberService;
import com.mzdevelopers.serverapplication.question.entity.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberRepository memberRepository, MemberService memberService, MemberMapper mapper) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.mapper = mapper;
    }

    // 로그인 한 멤버 아이디로 본인이 등록한 질문 가져오기
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMembersQuestions(@PathVariable("memberId") long memberId){

        MemberResponseDto response = new MemberResponseDto();

        List<Question> findQuestions = memberService.getQuestions(memberId);

        response.setMemberId(memberId);
        response.setQuestions(mapper.questionToMemberQuestionDto(findQuestions));

        return ResponseEntity.ok(response);
    }
}
