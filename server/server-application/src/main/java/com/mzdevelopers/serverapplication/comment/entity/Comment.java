package com.mzdevelopers.serverapplication.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Column(nullable = false)
    private String commentDetail;


    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    @JsonIgnore
    private Answer answer;

    @Column(nullable = false)
    private long memberId;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;
}
