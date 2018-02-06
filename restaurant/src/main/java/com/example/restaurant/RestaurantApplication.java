package com.example.restaurant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collection;

@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}


	@Bean
	public CommandLineRunner init(MongoOperations mongo){
		return (String... args)-> {
			mongo.dropCollection((Restaurant.class));
			mongo.createCollection(Restaurant.class, CollectionOptions.empty().size(1000).capped());

			mongo.save(new Restaurant("sush1", 10.00, "sushi"));
			mongo.save(new Restaurant("sush2", 5.00, "sushi"));
			mongo.save(new Restaurant("beef1", 20.00, "grill"));
			mongo.save(new Restaurant("beef2", 12.00, "grill"));


		};
	}
}
