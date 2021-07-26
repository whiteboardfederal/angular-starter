package com.whiteboard.angularstarter;

import com.whiteboard.angularstarter.api.GenericObject;
import com.whiteboard.angularstarter.api.ObjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class AngularStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularStarterApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ObjectRepository objectRepository) {
		return args -> {
			Stream.of("Item1", "Item2", "Item3", "Item4", "Item5").forEach(text -> {
				GenericObject item = new GenericObject(text);
				objectRepository.save(item);
			});
			System.out.println("DONE!");
		};
	}

}
