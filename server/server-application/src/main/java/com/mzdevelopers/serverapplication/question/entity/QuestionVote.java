package com.mzdevelopers.serverapplication.question.entity;

import com.mzdevelopers.serverapplication.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class QuestionVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionVoteId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "memberId")
    private Member member;

    private boolean isQuestionVoted;


    @Builder
    public QuestionVote(Question question, Member member) {
        this.question = question;
        this.member = member;
        this.isQuestionVoted = true;
    }

    public void updateVote() {
        this.isQuestionVoted = !isQuestionVoted;
    }


}
