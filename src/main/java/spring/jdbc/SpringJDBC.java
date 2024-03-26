package spring.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import spring.jdbc.entity.Breed;
import spring.jdbc.service.BreedService;

@SpringBootApplication
public class SpringJDBC implements CommandLineRunner {
	@Autowired
	private BreedService breedService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJDBC.class, args);

	}
	
	@Override
	public void run(String... args) throws Exception {
		List<Breed> breeds = breedService.retrieveAllBreeds();
		breeds.forEach(System.out::println);
	}
}
