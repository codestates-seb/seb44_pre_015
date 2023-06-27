package com.mzdevelopers.serverapplication.question.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.tag.entity.QuestionTag;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Question extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 50000)
    private String detail;

    @Column(nullable = false)
    private boolean solutionStatus;

    @Column(nullable = false)
    private int answerCount;

    @Column(nullable = false)
    private int votesCount;

    @Column(nullable = false)
    private int viewCount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<QuestionTag> questionTags;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionVote> questionVotes;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers;

    @Builder
    public Question(String title, String detail, Member member){
        this.title = title;
        this.detail = detail;
        this.member = member;
        this.solutionStatus = false;
        this.answerCount = 0;
        this.votesCount = 0;
        this.viewCount = 0;
    }

    public void update(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
    public void updateSelect(boolean solutionStatus){
        this.solutionStatus=solutionStatus;
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

    public void setMember(Member member) {
        this.member = member;
    }
}
