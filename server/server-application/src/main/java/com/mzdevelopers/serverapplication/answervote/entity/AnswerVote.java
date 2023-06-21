package com.mzdevelopers.serverapplication.answervote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.stub.MemberStub;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerVoteId;
    @Column
    private boolean answerVoted;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;

//    @Column
//    private long answerId;
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "memberId")
    private MemberStub memberStub;

    @Builder
    public AnswerVote(Answer answer, MemberStub memberStub) {
        this.answer = answer;
        this.memberStub = memberStub;
        this.answerVoted = true;
    }

    public void updateVote() {
        this.answerVoted = !answerVoted;
    }
}
