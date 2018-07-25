package com.inditex.hateoas.controller;


import com.inditex.hateoas.component.UserResource;
import com.inditex.hateoas.component.UserResourceAssembler;
import com.inditex.hateoas.dao.OrderRepository;
import com.inditex.hateoas.dao.UserRepository;
import com.inditex.hateoas.model.Order;
import com.inditex.hateoas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @GetMapping
    public HttpEntity<List<UserResource>> getAllUsers() {
        Iterable<? extends User> users = userRepository.findAll();
        UserResourceAssembler assembler = new UserResourceAssembler();
        List<UserResource> resources = assembler.toResources(users);
        return new HttpEntity<List<UserResource>>(resources);
    }

    @GetMapping(value = "/{userId}/orders")
    public Resources<Order> getOrdersForUser(@PathVariable final Integer userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        for (final Order order : orders) {
            Link selfLink = linkTo(methodOn(OrderController.class)
                    .getOrderById(order.getOrderId())).withSelfRel();
            order.add(selfLink);
        }

        Link link = linkTo(methodOn(UserController.class)
                .getOrdersForUser(userId)).withSelfRel();
        Resources<Order> result = new Resources<Order>(orders, link);
        return result;
    }


    @GetMapping("{userId}")
    public HttpEntity<UserResource> getUserById(@PathVariable("userId") Integer id) {
        User user = userRepository.getOne(id);
        UserResourceAssembler assembler = new UserResourceAssembler();
        UserResource resources = assembler.toResource(user);
        return new HttpEntity<UserResource>(resources);
    }

    @Autowired
    public UserController(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }
}
