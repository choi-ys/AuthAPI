package io.auth.api.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/** 용석 : 2021-01-11
 * Application 구동 관련 설정 부
 */
@Configuration
@Slf4j
public class ApplicationConfig {

    /** 용석 : 2020-01-11
     * 입력값을 통해 결정되거나, 시스템에서 자동으로 부여하는 항목들의 입력을 제한하기 위해
     * 분리한 입력객체와 Entity객체를 Mapping하기 위한 ModelMapper
     * @return : modelMapper Bean
     */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    /** 용석 : 2021-01-11
     * Spring Security에서 제공하는 암호화 모듈
     * 어떤 방식으로 암호화 됬는지 암호화 방식을 String pre-fix로 제공
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
