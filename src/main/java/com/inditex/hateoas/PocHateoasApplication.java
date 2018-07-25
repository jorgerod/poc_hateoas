package com.inditex.hateoas;

import com.inditex.hateoas.dao.OrderRepository;
import com.inditex.hateoas.dao.UserRepository;
import com.inditex.hateoas.model.Order;
import com.inditex.hateoas.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PocHateoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocHateoasApplication.class, args);
	}

	@Bean
    CommandLineRunner init(UserRepository userRepository, OrderRepository orderRepository) {
		return args -> {
			userRepository.save(User.builder()
                    .id(1)
                    .name("Frodo Bolson")
                    .age(20)
                    .build());
			userRepository.save(User.builder()
                    .id(2)
                    .name("Bilbo Bolson")
                    .age(100)
                    .build());
            userRepository.save(User.builder()
                    .id(3)
                    .name("Sam Gamyi")
                    .age(18)
                    .build());


			orderRepository.save(Order.builder()
					.orderId(10)
					.userId(1)
					.price(10)
					.quantity(10)
					.build());
			orderRepository.save(Order.builder()
					.orderId(11)
					.userId(1)
					.price(11)
					.quantity(11)
					.build());
			orderRepository.save(Order.builder()
					.orderId(12)
					.userId(1)
					.price(12)
					.quantity(12)
					.build());
		};
	}
}
