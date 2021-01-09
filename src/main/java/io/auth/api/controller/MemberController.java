package io.auth.api.controller;

import io.auth.api.constants.CustomMediaTypeConstants;
import io.auth.api.domain.dto.MemberRequest;
import io.auth.api.domain.entity.MemberEntity;
import io.auth.api.resource.ErrorsEntityModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
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
public class MemberController {

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity createMember(@RequestBody @Valid MemberRequest memberRequest, Errors errors){

        /* choi-ys : 2001-01-09
        * Request value Validation check to JSR303 Annotation
        * if Errors.hasError then return Bad Request Body to Serialized Errors Object
        * */
        if (errors.hasErrors()){
            return ResponseEntity.badRequest().body(new ErrorsEntityModel(errors));
        }

        // Mapping Request Object To Entity Object
        MemberEntity memberEntity = this.modelMapper.map(memberRequest, MemberEntity.class);

        // Create Self URI info
        URI selfUri = linkTo(MemberController.class).withSelfRel().toUri();

        // Return 201 Created and SelfUri
        return ResponseEntity.created(selfUri).body(memberEntity);
    }
}
