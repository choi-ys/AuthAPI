package io.auth.api.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.auth.api.constants.LinkRelationConstants.INDEX;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/index", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
public class IndexController {

    @GetMapping
    public RepresentationModel index(){
        RepresentationModel representationModel = new RepresentationModel();
        representationModel.add(linkTo(IndexController.class).withRel(INDEX));
        return representationModel;
    }
}
