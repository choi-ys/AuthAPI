package io.auth.api.controller;

import io.auth.api.constants.CustomMediaTypeConstants;
import io.auth.api.controller.common.BaseTest;
import io.auth.api.domain.dto.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerFailTest extends BaseTest {

    @Test
    @DisplayName("회원 생성 API : 빈값 요청 시 400 Bad Request 처리")
    public void createMemberAPI_EmptyParam() throws Exception {
        // Given
        MemberRequest memberRequest = MemberRequest.builder().build();
        String urlTemplate = "/api/member";

        // When
        ResultActions resultActions = this.mockMvc.perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(CustomMediaTypeConstants.HAL_JSON_UTF8_VALUE)
                .content(this.objectMapper.writeValueAsString(memberRequest))
        );

        // Then
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

        String urlTemplate = "/api/member";

        // When
        ResultActions resultActions = this.mockMvc.perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(CustomMediaTypeConstants.HAL_JSON_UTF8_VALUE)
                .content(this.objectMapper.writeValueAsString(memberRequest))
        );

        // Then
        resultActions.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors").exists())
        ;
    }
}