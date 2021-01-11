package io.auth.api.controller;

import io.auth.api.constants.CustomMediaTypeConstants;
import io.auth.api.domain.common.ProcessingResult;
import io.auth.api.domain.common.ProcessingResultEntityModel;
import io.auth.api.domain.dto.PartnerSignUp;
import io.auth.api.domain.entity.PartnerEntity;
import io.auth.api.resource.ErrorsEntityModel;
import io.auth.api.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


/** 용석:2021-01-09
 * 제휴사 정보 요청 처리부
 */
@RestController
@RequestMapping(value = "/api/member", consumes = MediaType.APPLICATION_JSON_VALUE, produces = CustomMediaTypeConstants.HAL_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class PartnerController {

    private final ModelMapper modelMapper;
    private final PartnerService partnerService;

    /** 용석:2021-01-09
     * 제휴사 계정 생성 요청 처리 부
     * @param partnerSignUp
     *  -> 유효성 검사 : JSR 303을 이용한 기본 유효성 검사
     * @param errors
     * @return
     *  -> 201 Creatd
     *  -> 400 Bad Reqeust
     *  -> 500 Server Error
     */
    @PostMapping
    public ResponseEntity createMember(@RequestBody @Valid PartnerSignUp partnerSignUp, Errors errors){

        /* choi-ys : 2001-01-09
        * Request value Validation check to JSR303 Annotation
        * if Errors.hasError then return Bad Request Body to Serialized Errors Object
        * */
        if (errors.hasErrors()){
            return ResponseEntity.badRequest().body(new ErrorsEntityModel(errors));
        }

        // Mapping Request Object To Entity Object
        PartnerEntity partnerEntity = this.modelMapper.map(partnerSignUp, PartnerEntity.class);

        // Process Create Member
        ProcessingResult processingResult = this.partnerService.createPartner(partnerEntity);
        ProcessingResultEntityModel processingResultEntityModel = new ProcessingResultEntityModel(processingResult);

        // Return Response by Processing Result
        if(processingResult.isSuccess()){
            // Return 201 Created and Location info When Process is success
            URI createdUri = linkTo(PartnerController.class).withSelfRel().toUri();
            return ResponseEntity.created(createdUri).body(processingResultEntityModel);
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(processingResultEntityModel);
        }
    }

    private ResponseEntity<ErrorsEntityModel> badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsEntityModel(errors));
    }
}
