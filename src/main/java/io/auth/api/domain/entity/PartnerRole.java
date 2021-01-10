package io.auth.api.domain.entity;

public enum PartnerRole {
    FORBIDDEN, STARTER, TRIAL, STANDARD, PREMIUM
}

//    FORBIDDEN,          // API 접근 권한 없음
//    STARTER,            // 기본 API 접근 가능
//    TRIAL,              // 일정량의 API 통신 허용
//    STANDARD,           // ?? -> 허용량 or 허용 API 범위
//    PREMIUM             // ?? -> 허용량 or 허용 API 범위
//    OR
//    DISPATCH,       // 배차
//    BOARDING,       // 탑승
//    STATICS        // 통계
