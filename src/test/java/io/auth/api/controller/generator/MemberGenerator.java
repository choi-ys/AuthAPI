package io.auth.api.controller.generator;

import io.auth.api.domain.dto.MemberRequest;
import org.springframework.stereotype.Component;

@Component
public class MemberGenerator {

    public MemberRequest generatorMemberRequest(){
        String email = "email";
        String password = "password";
        String name = "name";

        return MemberRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
