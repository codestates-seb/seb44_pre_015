package com.mzdevelopers.serverapplication.answer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "ANSWERS")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private int votesCount=0;

    @Column(nullable = false)
    private boolean solutionStatus=false;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "QUESTION_ID")
//    private Question question;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;
//
//    public long getQuesetionId(){
//        return question.getQuestionId();
//    }
//
//    public long getMemberId(){
//        return member.getMemberId();
//    }

    @Column(nullable = false) //스텁데이터
    private long questionId;

    @Column(nullable = false) //스텁데이터
    private long memberId;
}
