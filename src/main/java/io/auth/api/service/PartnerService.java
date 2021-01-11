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
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerService implements UserDetailsService {

    private final PartnerRepository partnerRepository;

    public ProcessingResult createPartner(PartnerEntity partnerEntity) {
        ProcessingResult processingResult = new ProcessingResult();
        PartnerEntity createdPartnerEntity;
        try {
            createdPartnerEntity = partnerRepository.save(partnerEntity);
            return processingResult.processSuccess(createdPartnerEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return processingResult.processFail(Error.builder()
                    .code(-1)
                    .detail(e.getMessage())
                    .Msg("회원 가입에 실패하였습니다.")
                    .build());
        }
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
        String grantedAuthority = "ROLE" + partnerEntity.getRoles();
//        return new User(partnerEntity.getId(), partnerEntity.getPassword(), grantedAuthority));
        return new User(partnerEntity.getId(), partnerEntity.getPassword(), this.roleToAuthorities(partnerEntity.getRoles()));
    }

    private Collection<? extends GrantedAuthority> roleToAuthorities(Set<PartnerRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE" + role.name()))
                .collect(Collectors.toSet());
    }

}
