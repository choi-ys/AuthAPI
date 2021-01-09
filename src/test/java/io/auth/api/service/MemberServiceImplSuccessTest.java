package io.auth.api.service;

import io.auth.api.domain.common.ProcessingResult;
import io.auth.api.domain.entity.MemberEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class MemberServiceImplSuccessTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원 생성 Service")
    public void createMemberService(){
        // Given
        String email = "project.log.062";
        String password = "username";
        String name = "remark";

        MemberEntity memberEntity = MemberEntity.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();

        // When
        ProcessingResult createdProcessingResult = this.memberService.createMemberProcess(memberEntity);

        // Then
        assertThat(createdProcessingResult).isNotNull();
        assertThat(createdProcessingResult.isSuccess()).isEqualTo(true);
        assertThat(createdProcessingResult.getData()).isNotNull();
    }
}