package io.auth.api.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingResult<T> {

    @JsonIgnore
    boolean success = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Error error;

    T data;

    public ProcessingResult processFail(Error error){
        this.error = error;
        this.success = false;
        return this;
    }

    public ProcessingResult processSuccess(T data){
        this.data = data;
        return this;
    }

}
