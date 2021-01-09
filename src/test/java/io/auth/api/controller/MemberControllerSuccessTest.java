package io.auth.api.controller;

import io.auth.api.controller.common.BaseTest;
import io.auth.api.domain.dto.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MemberControllerSuccessTest extends BaseTest {

    @Test
    @DisplayName("회원 생성 API")
    public void createMemberAPI() throws Exception {
        // Given
        String email = "project.log.062@gmail.com";
        String password = "chldydtjr1!";
        String name = "최용석";

        MemberRequest memberRequest = MemberRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
        String urlTemplate = "/api/member";

        // When
        ResultActions resultActions = this.mockMvc.perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaTypes.HAL_JSON_VALUE)
                .content(this.objectMapper.writeValueAsString(memberRequest))
        );

        // Then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.CONTENT_TYPE))
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("_links").exists())
                .andExpect(jsonPath("data.id").value("1"))
                .andExpect(jsonPath("data.email").exists())
                .andExpect(jsonPath("data.name").exists())
        ;
    }
}