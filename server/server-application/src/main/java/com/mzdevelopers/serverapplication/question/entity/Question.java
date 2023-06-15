package com.mzdevelopers.serverapplication.question.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

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

}
