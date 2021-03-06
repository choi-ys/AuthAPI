package io.auth.api.domain.entity;

public enum PartnerStatus {
    DRAFT,                  // 제휴 진행 전 상태 [기본값] : 기본적인 API 통신만이 허용된 STARTER 권한
    PRE_ALLIANCE,           // 제휴 체험 상태 : API 허용량이 제한된 TRIAL 권한
    ALLIANCE,               // 제휴 진행 상태 : 과금방식에 따라 API 허용량이 다른 STANDARD, PREMINUM의 권한
    EXPIRATION,             // 제휴 만료 상태 : FORBIDDEN 권한
    STOP_USING_BY_ADMIN,    // 사내 관리자에 의한 제휴 정지 -> FORBIDDEN 권한
    STOP_USING_BY_PARTNER   // 파트너사 요청에 의한 제휴 정지 -> STARTER 권한
}
