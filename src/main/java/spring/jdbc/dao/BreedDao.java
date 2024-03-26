package spring.jdbc.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import spring.jdbc.entity.AltName;
import spring.jdbc.entity.Breed;
import spring.jdbc.entity.Category;

@Component
public class BreedDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Breed> findAll() {
		List<Breed> breeds = findAllBreeds();
		
		for(Breed breed : breeds) {
			breed.getAltNames().addAll(findAllAltNames(breed.getBreedId()));
			breed.getCategories().addAll(findCategories(breed.getBreedId()));
		}
		
		return breeds;
	} 

	private List<Category> findCategories(Long breedId) {
		String sql = """
				SELECT *
				FROM category
				JOIN breed_category bc USING(category_id)
				WHERE bc.breed_id = :breed_id
				""";
		
		Map<String, Object> parms = Map.of("breed_id", breedId);
		
		return jdbcTemplate.query(sql, parms, (rs, rowNum) -> {
			Category category = new Category();
			
			category.setCategoryId(rs.getLong("category_id"));
			category.setCategoryName(rs.getString("category_name"));
			
			return category;
		});
	}

	private List<AltName> findAllAltNames(Long breedId) {
		String sql = """
				SELECT *
				FROM alt_name
				WHERE breed_id = :breed_id
				""";
		
		Map<String, Object> parms = Map.of("breed_id", breedId);
		
		return jdbcTemplate.query(sql, parms, (rs, rowNum) -> {
			AltName altName = new AltName();
			
			altName.setAlternateId(rs.getLong("alternate_id"));
			altName.setBreedId(rs.getLong("breed_id"));
			altName.setAlternateName(rs.getString("alternate_name"));
			
			return altName;
		});
	}

	private List<Breed> findAllBreeds() {
		String sql = """
				SELECT *
				FROM breed
				""";
		
//		return jdbcTemplate.query(sql, new RowMapper<>() {
//			@Override
//			public Breed mapRow(ResultSet rs, int rowNum) throws SQLException {
//				// TODO Auto-generated method stub
//				return null;
//			}});
			
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Breed breed = new Breed();
			
			breed.setBreedId(rs.getLong("breed_id"));
			breed.setBreedName(rs.getString("breed_name"));
			breed.setDescription(rs.getString("description"));
			
			return breed;
		});
	}
}