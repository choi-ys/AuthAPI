package io.auth.api.domain.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PartnerSignUp {

    @NotBlank
    @Length(min = 3, max = 20)
    private String id;

    @NotBlank
    @Length(min = 8, max = 50)
    private String password;

    @Email
    @NotBlank
    private String partnerEmail;

    @NotBlank
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String partnerCompanyName;
}
