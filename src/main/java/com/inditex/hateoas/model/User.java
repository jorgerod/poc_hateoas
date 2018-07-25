package com.inditex.hateoas.model;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User extends ResourceSupport {

    @Id
    private Integer userId;
    private String name;
    private int age;
}
