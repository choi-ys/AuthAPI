package io.auth.api.domain.common;

import io.auth.api.controller.IndexController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static io.auth.api.constants.LinkRelationConstants.INDEX;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class ProcessingResultEntityModel extends EntityModel<ProcessingResult> {

    public ProcessingResultEntityModel(ProcessingResult content, Link... links) {
        super(content, links);
        add(linkTo(IndexController.class).withRel(INDEX));
    }
}
