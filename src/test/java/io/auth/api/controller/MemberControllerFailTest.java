package io.auth.api.controller;

import io.auth.api.constants.CustomMediaTypeConstants;
import io.auth.api.controller.common.BaseTest;
import io.auth.api.controller.generator.MemberGenerator;
import io.auth.api.domain.dto.MemberRequest;
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

class MemberControllerFailTest extends BaseTest {

    @Resource
    MemberGenerator memberGenerator;

    private MemberRequest memberRequest;
    private String urlTemplate = "/api/member";

    @BeforeEach
    public void setup(){
        this.memberRequest = memberGenerator.generatorMemberRequest();
    }

    @Test
    @DisplayName("회원 생성 API : 빈값 요청 시 400 Bad Request 처리")
    public void createMemberAPI_EmptyParam() throws Exception {
        // Given : Given Empty Param
        MemberRequest memberRequest = MemberRequest.builder().build();

        // When
        ResultActions resultActions = this.mockMvc.perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(CustomMediaTypeConstants.HAL_JSON_UTF8_VALUE)
                .content(this.objectMapper.writeValueAsString(memberRequest))
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
        String name = "";
        String email = "project.log.062";

        MemberRequest memberRequest = MemberRequest.builder()
                .email(email)
                .name(name)
                .build();

        // When
        ResultActions resultActions = this.mockMvc.perform(post(this.urlTemplate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(CustomMediaTypeConstants.HAL_JSON_UTF8_VALUE)
                .content(this.objectMapper.writeValueAsString(memberRequest))
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