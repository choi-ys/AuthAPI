package io.auth.api.service;

import io.auth.api.domain.common.ProcessingResult;
import io.auth.api.domain.entity.MemberEntity;

public interface MemberService {

    ProcessingResult createMemberProcess(MemberEntity memberEntity);
}
