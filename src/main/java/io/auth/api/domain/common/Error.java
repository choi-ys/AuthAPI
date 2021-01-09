package io.auth.api.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Error {

    private int code;           // 정의된 Error Code
    private String Msg;         // Client에게 전달될 내용
    private String detail;      // Exception data
}
