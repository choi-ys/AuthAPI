package io.auth.api.service;

import io.auth.api.domain.common.Error;
import io.auth.api.domain.common.ProcessingResult;
import io.auth.api.domain.entity.MemberEntity;
import io.auth.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public ProcessingResult createMemberProcess(MemberEntity memberEntity) {
        ProcessingResult processingResult = new ProcessingResult();
        MemberEntity createdMemberEntity;
        try {
            createdMemberEntity = memberRepository.save(memberEntity);
            return processingResult.processSuccess(createdMemberEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return processingResult.processFail(Error.builder()
                    .code(-1)
                    .detail(e.getMessage())
                    .Msg("회원 가입에 실패하였습니다.")
                    .build());
        }
    }
}
