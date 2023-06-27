package com.mzdevelopers.serverapplication.member.entity;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.question.entity.Question;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor //추가
@NoArgsConstructor
@Entity
@Builder //추가
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column( unique = true)
    private String name;

    @Column( unique = true)
    private String email;

    private String picture;

    @Column
    private int answerVoteCount = 0;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Question> questions;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Answer> answers;




}
