package tech.sosa.ingweb.application.director.service;

import java.security.InvalidParameterException;
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
		
		checkRequestIsNotEmpty(request);
		
		Collection<Director> directors = repository.all();
		
		directors = filterDirectors(request, directors);
		
		return directors;
	}

	private Collection<Director> filterDirectors(SearchDirectorsRequest request, Collection<Director> directors) {
		if(request.partialName != null) {
			directors = directors.stream()
					.filter(d -> d.fullName().toString().contains(request.partialName))
					.collect(Collectors.toList());
		}
		return directors;
	}

	private void checkRequestIsNotEmpty(SearchDirectorsRequest request) {
		if(request.partialName == null) {
			throw new InvalidParameterException("At least one parameter must be setted.");
		}
	}
	
}
