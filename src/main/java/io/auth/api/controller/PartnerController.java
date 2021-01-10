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


@RestController
@RequestMapping(value = "/api/member", consumes = MediaType.APPLICATION_JSON_VALUE, produces = CustomMediaTypeConstants.HAL_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class PartnerController {

    private final ModelMapper modelMapper;
    private final PartnerService partnerService;

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

        // Create Self URI info
        URI createdUri = linkTo(PartnerController.class).withSelfRel().toUri();
        ProcessingResultEntityModel processingResultEntityModel = new ProcessingResultEntityModel(processingResult);

        // Return 201 Created and SelfUri
        return ResponseEntity.created(createdUri).body(processingResultEntityModel);
    }

    private ResponseEntity<ErrorsEntityModel> badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsEntityModel(errors));
    }
}