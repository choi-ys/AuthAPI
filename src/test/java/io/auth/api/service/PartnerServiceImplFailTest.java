package io.auth.api.service;

import io.auth.api.domain.common.ProcessingResult;
import io.auth.api.domain.entity.PartnerEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class PartnerServiceFailTest {

    @Autowired
    PartnerService partnerService;

    @Test
    @DisplayName("회원 생성 로직 : 빈값 저장 시 오류 처리")
    public void createMemberService_EmptyParam(){
        // Given
        PartnerEntity partnerEntity = PartnerEntity.builder()
                .build();

        // When
        ProcessingResult createdProcessingResult = this.partnerService.createPartner(partnerEntity);

        // Then
        assertThat(createdProcessingResult).isNotNull();
    }

    @Test
    @DisplayName("회원 생성 Service : 잘못된 값 저장 시 오류 처리")
    public void createMemberService_WrongParam(){
        // Given
        PartnerEntity partnerEntity = PartnerEntity.builder()
                .build();

        // When
        ProcessingResult createdProcessingResult = this.partnerService.createPartner(partnerEntity);

        // Then
        assertThat(createdProcessingResult).isNotNull();
        assertThat(createdProcessingResult.isSuccess()).isEqualTo(false);
        assertThat(createdProcessingResult.getError()).isNotNull();
        assertThat(createdProcessingResult.getError().getDetail()).isNotBlank();
    }
}