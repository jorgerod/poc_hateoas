package com.inditex.hateoas.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inditex.hateoas.model.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResource extends Resource {

//    Resources<ProductResource> products;

    public UserResource(User content, Link... links) {
        super(content, links);
    }

}
