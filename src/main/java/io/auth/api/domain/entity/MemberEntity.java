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

    String username;

    String name;

    String remark;

    @Builder
    public MemberEntity(String name, String username, String remark) {
        this.name = name;
        this.username = username;
        this.remark = remark;
    }
}
