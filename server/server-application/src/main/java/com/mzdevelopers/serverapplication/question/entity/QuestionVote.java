package com.mzdevelopers.serverapplication.question.entity;

import com.mzdevelopers.serverapplication.question.stub.MemberStub;
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
    private MemberStub memberStub;

    private boolean isQuestionVoted;


    @Builder
    public QuestionVote(Question question, MemberStub memberStub) {
        this.question = question;
        this.memberStub = memberStub;
        this.isQuestionVoted = true;
    }

    public void updateVote() {
        this.isQuestionVoted = !isQuestionVoted;
    }


}
