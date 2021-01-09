package io.auth.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @Column(unique = true)
    @NotBlank
    String email;

    @JsonIgnore
    @NotBlank
    String password;

    @NotBlank
    String name;

    @Builder
    public MemberEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
