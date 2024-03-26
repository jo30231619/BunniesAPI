package spring.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.jdbc.dao.BreedDao;
import spring.jdbc.entity.Breed;

@Service
public class BreedService {
	@Autowired
	private BreedDao breedDao;
	
	@Transactional
	public List<Breed> retrieveAllBreeds() {
		return breedDao.findAll();
	}
}
