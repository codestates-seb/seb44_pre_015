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
@DynamicUpdate //추가
@Builder //추가
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "answer_vote_count", nullable = false)
    private int answer_vote_count;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Question> questions;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Answer> answers;

}
