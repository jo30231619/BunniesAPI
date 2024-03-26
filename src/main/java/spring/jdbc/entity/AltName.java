package spring.jdbc.entity;

import lombok.Data;

@Data
public class AltName {
	private Long alternateId;
	private Long breedId;
	private String alternateName;
}
