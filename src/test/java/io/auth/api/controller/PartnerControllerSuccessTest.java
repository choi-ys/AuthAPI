package io.auth.api.controller;

import io.auth.api.controller.common.BaseTest;
import io.auth.api.domain.dto.PartnerSignUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PartnerControllerSuccessTest extends BaseTest {

    @Test
    @DisplayName("회원 생성 API")
    public void createPartnerAPI() throws Exception {
        // Given
        String id = "project062";
        String password = "chldydtjr1!";
        String partnerEmail = "project.log.062@gmail.com";
        String partnerCompanyName = "naver";

        PartnerSignUp partnerSignUp = PartnerSignUp.builder()
                .id(id)
                .password(password)
                .partnerEmail(partnerEmail)
                .partnerCompanyName(partnerCompanyName)
                .build();
        String urlTemplate = "/api/member";

        // When
        ResultActions resultActions = this.mockMvc.perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaTypes.HAL_JSON_VALUE)
                .content(this.objectMapper.writeValueAsString(partnerSignUp))
        );

        // Then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.CONTENT_TYPE))
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("_links").exists())
                .andExpect(jsonPath("data.partnerNo").value("1"))
                .andExpect(jsonPath("data.id").exists())
                .andExpect(jsonPath("data.partnerEmail").exists())
                .andExpect(jsonPath("data.partnerCompanyName").exists())
        ;
    }
}