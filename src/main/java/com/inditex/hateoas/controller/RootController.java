package com.inditex.hateoas.controller;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/root")
public class RootController {

    @GetMapping()
    public HttpEntity<List<Link>> showLinks() {
        return new HttpEntity<List<Link>>(new ArrayList() {{
            add(linkTo(UserController.class).withRel("user"));
//            add(linkTo(methodOn(PersonController.class).show(1L)).withRel("person"));

            add(linkTo(OrderController.class).withRel("order"));
        }});
    }

}
