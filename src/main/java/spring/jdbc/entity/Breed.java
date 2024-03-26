package spring.jdbc.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Breed {
	private Long breedId;
	private String breedName;
	private String description;
	private Set<AltName> altNames = new HashSet<>();
	private Set<Category> categories = new HashSet<>();
}
