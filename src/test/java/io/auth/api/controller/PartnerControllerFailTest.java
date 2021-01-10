package io.auth.api.controller;

import io.auth.api.constants.CustomMediaTypeConstants;
import io.auth.api.controller.common.BaseTest;
import io.auth.api.controller.generator.PartnerGenerator;
import io.auth.api.domain.dto.PartnerSignUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PartnerControllerFailTest extends BaseTest {

    @Resource
    PartnerGenerator partnerGenerator;

    private PartnerSignUp partnerSignUp;
    private String urlTemplate = "/api/member";

    @BeforeEach
    public void setup(){
        this.partnerSignUp = partnerGenerator.generatorPartnerSignUp();
    }

    @Test
    @DisplayName("회원 생성 API : 빈값 요청 시 400 Bad Request 처리")
    public void createMemberAPI_EmptyParam() throws Exception {
        // Given : Given Empty Param
        PartnerSignUp partnerSignUp = PartnerSignUp.builder().build();

        // When
        ResultActions resultActions = this.mockMvc.perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(CustomMediaTypeConstants.HAL_JSON_UTF8_VALUE)
                .content(this.objectMapper.writeValueAsString(partnerSignUp))
        );

        // Then : Check http status code is 400 Bad Request
        resultActions.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors").exists())
        ;
    }

    @Test
    @DisplayName("회원 생성 API : 잘못된 입력값 요청 시 400 Bad Request 처리")
    public void createMemberAPI_WrongParam() throws Exception {
        // Given
        String id = "project062";
        String password = "chldydtjr1!";
        String partnerEmail = "project.log.062";
        String partnerCompanyName = "naver";

        PartnerSignUp partnerSignUp = PartnerSignUp.builder()
                .id(id)
                .password(password)
                .partnerEmail(partnerEmail)
                .partnerCompanyName(partnerCompanyName)
                .build();

        // When
        ResultActions resultActions = this.mockMvc.perform(post(this.urlTemplate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(CustomMediaTypeConstants.HAL_JSON_UTF8_VALUE)
                .content(this.objectMapper.writeValueAsString(partnerSignUp))
        );

        // Then
        resultActions.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().exists(HttpHeaders.CONTENT_TYPE))
                .andExpect(jsonPath("errors[0].objectName").exists())
                .andExpect(jsonPath("errors[0].field").exists())
                .andExpect(jsonPath("errors[0].code").exists())
                .andExpect(jsonPath("errors[0].defaultMessage").exists())
                .andExpect(jsonPath("_links.index").exists())
        ;
    }
}