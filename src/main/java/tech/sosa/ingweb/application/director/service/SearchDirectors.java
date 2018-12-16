package tech.sosa.ingweb.application.director.service;

import java.util.Collection;
import java.util.stream.Collectors;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorRepository;

public class SearchDirectors {

	private DirectorRepository repository;

	public SearchDirectors(DirectorRepository repository) {
		this.repository = repository;
	}
	
	public Collection<Director> execute(SearchDirectorsRequest request) {
		
		Collection<Director> directors = repository.all();
		
		if (request.isEmpty()) return directors;
		
		directors = filterDirectors(request, directors);
		
		return directors;
	}

	private Collection<Director> filterDirectors(SearchDirectorsRequest request, Collection<Director> directors) {
		
		return directors.stream()
				.filter(d -> d.fullName().toString().contains(request.partialName))
				.collect(Collectors.toList());
	}
	
}
