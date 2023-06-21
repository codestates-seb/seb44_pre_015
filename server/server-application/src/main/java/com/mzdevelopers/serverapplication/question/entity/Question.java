package com.mzdevelopers.serverapplication.question.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.tag.entity.QuestionTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Question extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private boolean solutionStatus;

    @Column(nullable = false)
    private int answerCount;

    @Column(nullable = false)
    private int votesCount;

    @Column(nullable = false)
    private int viewCount;

    @Column(nullable = false)
    private Long memberId;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<QuestionTag> questionTags;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionVote> questionVotes;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @Builder
    public Question(String title, String detail, Long memberId){
        this.title = title;
        this.detail = detail;
        this.memberId = memberId;
        this.solutionStatus = false;
        this.answerCount = 0;
        this.votesCount = 0;
        this.viewCount = 0;
    }

    public void update(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public void updateVoteCount(boolean voted) {
        if (voted) {
            votesCount++;
        } else {
            votesCount--;
        }
    }

    public void increaseView() {
        this.viewCount++;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<Answer> getAnswers() {
        Hibernate.initialize(answers);
        return answers;
    }
}
