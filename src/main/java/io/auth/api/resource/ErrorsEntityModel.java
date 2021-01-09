package io.auth.api.resource;

import io.auth.api.controller.IndexController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.Errors;

import static io.auth.api.constants.LinkRelationConstants.INDEX;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class ErrorsEntityModel extends EntityModel<Errors> {
    public ErrorsEntityModel(Errors errors, Link... links) {
        super(errors, links);
        add(linkTo(IndexController.class).withRel(INDEX));
    }
}
