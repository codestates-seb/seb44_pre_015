package com.mzdevelopers.serverapplication.answer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.comment.entity.Comment;
import com.mzdevelopers.serverapplication.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

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
    private int votesCount;

    @Column(nullable = false)
    private boolean solutionStatus=false;

    @OneToMany(mappedBy = "answer")
    private List<Comment> comments;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @Column(nullable = false) //스텁데이터
    private long memberId;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AnswerVote> answerVotes;

    public void updateVoteCount(boolean voted) {
        if (voted) {
            votesCount++;
        } else {
            votesCount--;
        }
    }
}
