package io.auth.api.controller;

import io.auth.api.constants.CustomMediaTypeConstants;
import io.auth.api.domain.dto.MemberRequest;
import io.auth.api.domain.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping(value = "/api/member", consumes = MediaType.APPLICATION_JSON_VALUE, produces = CustomMediaTypeConstants.HAL_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class MemberController {

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity createMember(@RequestBody MemberRequest memberRequest){
        // TODO : Request value validation -> JSR 303 Annotaion
        // TODO : if Errors.hasError -> return Bad Request Body To Serialized Errors

        // Mapping Request Object To Entity Object
        MemberEntity memberEntity = this.modelMapper.map(memberRequest, MemberEntity.class);

        // Create Self URI info
        URI selfUri = linkTo(MemberController.class).withSelfRel().toUri();

        // Return 201 Created and SelfUri
        return ResponseEntity.created(selfUri).body(memberEntity);
    }
}
