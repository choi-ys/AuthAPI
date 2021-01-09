package io.auth.api.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String email;
    String password;
    String name;

    @Builder
    public MemberEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
