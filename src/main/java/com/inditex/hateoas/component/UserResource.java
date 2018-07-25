package com.inditex.hateoas.component;

import com.inditex.hateoas.model.User;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {
    public UserResource(User entity) {

    }
}
