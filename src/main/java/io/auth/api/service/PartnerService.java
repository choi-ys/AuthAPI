package io.auth.api.service;

import io.auth.api.domain.common.Error;
import io.auth.api.domain.common.ProcessingResult;
import io.auth.api.domain.entity.PartnerEntity;
import io.auth.api.domain.entity.PartnerRole;
import io.auth.api.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/** 용석:2021-01-11
 * 제휴사 관련 기능 처리 부
 * -> Success TC : PartnerServiceSuccessTest.java
 * -> Fail TC : PartnerServiceFailTest.java
 */
@Service
@RequiredArgsConstructor
public class PartnerService implements UserDetailsService {

    private final PartnerRepository partnerRepository;
    private final PasswordEncoder passwordEncoder;

    /** 용석
     * 파트너 정보 생성
     *  - 처리내용
     *    -> SpringSecurity의 PasswordEncoder를 이용한 bcript 암호화 [기본값 : bcript]
     *    -> {@Link ProcessingResult}객체를 이용한 처리 결과 반환
     * @param partnerEntity
     * @return
     */
    public ProcessingResult createPartner(PartnerEntity partnerEntity) {
        ProcessingResult processingResult = new ProcessingResult();
        PartnerEntity createdPartnerEntity;
        try {
            /* Partner Entity의 id 중복 검사 */
            if(this.isDuplicatedId(partnerEntity.getId())){
                return processingResult.processFail(Error.builder()
                        .code(-1)
                        .detail("ERROR Duplicated Partner")
                        .Msg("이미 존재하는 회원입니다.")
                        .build());
            }

            /* SpringSecurity의 PasswordEncoder를 이용한 bcript 암호화 [기본값] */
            partnerEntity.setPassword(this.passwordEncoder.encode(partnerEntity.getPassword()));
            createdPartnerEntity = partnerRepository.save(partnerEntity);
            return processingResult.processSuccess(createdPartnerEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return processingResult.processFail(Error.builder()
                    .code(-1)
                    .detail(e.getMessage())
                    .Msg("회원 가입에 실패하였습니다. 관리자에게 문의 하세요.")
                    .build());
        }
    }

    public boolean isDuplicatedId(String id){
        return partnerRepository.countById(id) == 0 ? false : true;
    }

    /**
     * Application에서 정의한 Account domain을 Spring security에서 정의한 UsertDetail Interface로 변환
     * @param id
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        PartnerEntity partnerEntity = partnerRepository.findById(id)
                /**
                 * 요청 파라미터에 해당하는 Account 객체 조회 실패 시 오류 반환 처리
                 * - userName(account.email)에 해당 하는 Account 객체 조회 실패 시 Null을 반환 하므로
                 * - Srping security의 UsernameNotFoundException객체를 통해 정의된 오류를 반환한다.
                 */
                .orElseThrow(() -> new UsernameNotFoundException(id));

        /**
         * Srping security의 User객체를 이용하여 MemberEntity객체를 UserDetails 객체로 변환
         *  - UserDetails interface로 객체 변환 처리를 구현할 경우 모든 메소드를 구현 해야하므로,
         *  - UserDetails의 User객체를 이용하여 MemberEntity 체를 Spring Security의 UserDetails 객체로 변환한다.
         */
        return new User(partnerEntity.getId(), partnerEntity.getPassword(), this.roleToAuthorities(partnerEntity.getRoles()));
    }

    private Collection<? extends GrantedAuthority> roleToAuthorities(Set<PartnerRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE" + role.name()))
                .collect(Collectors.toSet());
    }

}
