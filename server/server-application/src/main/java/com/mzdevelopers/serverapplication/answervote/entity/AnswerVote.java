package com.mzdevelopers.serverapplication.answervote.entity;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.member.entity.Member;
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


    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "memberId")
    private Member member;

    @Builder
    public AnswerVote(Answer answer, Member member) {
        this.answer = answer;
        this.member = member;
        this.answerVoted = true;
    }

    public void updateVote() {
        this.answerVoted = !answerVoted;
    }
}
