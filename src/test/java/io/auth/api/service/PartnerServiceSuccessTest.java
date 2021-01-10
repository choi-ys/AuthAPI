package io.auth.api.service;

import io.auth.api.domain.common.ProcessingResult;
import io.auth.api.domain.entity.PartnerEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class PartnerServiceSuccessTest {

    @Autowired
    PartnerService partnerService;

    @Test
    @DisplayName("회원 생성 Service")
    public void createMemberService(){
        // Given
        String id = "project.062";
        String password = "chldydtjr1!";
        String partnerEmail = "project.log.062@gmail.com";
        String partnerCompanyName = "naver";

        PartnerEntity partnerEntity = PartnerEntity.builder()
                .id(id)
                .password(password)
                .partnerEmail(partnerEmail)
                .partnerCompanyName(partnerCompanyName)
                .build();

        // When
        ProcessingResult createdProcessingResult = this.partnerService.createPartner(partnerEntity);

        // Then
        assertThat(createdProcessingResult).isNotNull();
        assertThat(createdProcessingResult.isSuccess()).isEqualTo(true);
        assertThat(createdProcessingResult.getData()).isNotNull();
    }

    @Test
    public void findById(){
        UserDetailsService userDetailsService = partnerService;

    }
}