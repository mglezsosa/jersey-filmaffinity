package tech.sosa.ingweb.application.director.service;

import java.util.Collection;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorRepository;

public class SearchDirectors {

	private DirectorRepository repository;

	public SearchDirectors(DirectorRepository repository) {
		this.repository = repository;
	}
	
	public Collection<Director> execute(SearchDirectorsRequest request) {
		
		if (request.isEmpty()) return repository.all();
		
		return repository.ofSpecs(request.partialName);
	}
	
}
