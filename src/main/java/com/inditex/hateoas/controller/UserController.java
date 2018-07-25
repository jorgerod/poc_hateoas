package com.inditex.hateoas.controller;


import com.inditex.hateoas.dao.OrderRepository;
import com.inditex.hateoas.dao.UserRepository;
import com.inditex.hateoas.model.Order;
import com.inditex.hateoas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/users/", produces = "application/hal+json")
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class UserController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @GetMapping
    public Resources<User> getAllUsers() {
//        return ResponseEntity.ok(userRepository.findAll());

        List<User> users = userRepository.findAll();
        Link link = linkTo(UserController.class).withSelfRel();

        for (User user : users) {
            Integer userId = user.getUserId();
            Link selfLink = linkTo(UserController.class).slash(userId).withSelfRel();
            user.add(selfLink);
//            if (orderRepository.getAllOrdersForUser(userId).size() > 0) {
                Link ordersLink = linkTo(methodOn(UserController.class)
                        .getOrdersForUser(userId)).withRel("allOrders");
                user.add(ordersLink);
//            }
        }


        return new Resources<User>(userRepository.findAll(), link);
    }

    @GetMapping(value = "/{userId}/orders", produces = {"application/hal+json"})
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
    public ResponseEntity<User> getUserById(@PathVariable("userId") Integer id) {
        return ResponseEntity.ok(userRepository.getOne(id));
    }

    @Autowired
    public UserController(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }
}
