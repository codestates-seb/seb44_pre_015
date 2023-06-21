package com.mzdevelopers.serverapplication.member.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

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

}
