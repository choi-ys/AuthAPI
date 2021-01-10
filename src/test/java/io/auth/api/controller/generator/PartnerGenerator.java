package io.auth.api.controller.generator;

import io.auth.api.domain.dto.PartnerSignUp;
import io.auth.api.domain.entity.PartnerEntity;
import org.springframework.stereotype.Component;

@Component
public class PartnerGenerator {

    public PartnerSignUp generatorPartnerSignUp(){
        String id = "project.062";
        String password = "chldydtjr1!";
        String partnerEmail = "project.log.062";
        String partnerCompanyName = "naver";

        return PartnerSignUp.builder()
                .id(id)
                .password(password)
                .partnerEmail(partnerEmail)
                .partnerCompanyName(partnerCompanyName)
                .build();
    }

    public PartnerEntity generatorPartnerEntity(){
        String id = "noel";
        String password = "chldydtjr1!";
        String partnerEmail = "project.log.062@gmail.com";
        String partnerCompanyName = "naver";

        PartnerEntity partnerEntity = PartnerEntity.builder()
                .id(id)
                .password(password)
                .partnerEmail(partnerEmail)
                .partnerCompanyName(partnerCompanyName)
                .build();
        partnerEntity.signUp();

        return partnerEntity;
    }
}
