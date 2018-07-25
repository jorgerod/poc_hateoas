package com.inditex.hateoas.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity(name = "orders")
public class Order extends ResourceSupport {

    @Id
    private Integer orderId;
    private double price;
    private int quantity;

    private Integer userId;
}
