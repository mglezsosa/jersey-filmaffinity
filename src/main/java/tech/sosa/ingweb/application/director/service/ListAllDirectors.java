package tech.sosa.ingweb.application.director.service;

import java.util.Collection;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorRepository;

public class ListAllDirectors {

	private DirectorRepository directorRepository;

	public ListAllDirectors(DirectorRepository directorRepository) {
		this.directorRepository = directorRepository;
	}
	
	public Collection<Director> execute() {
		return directorRepository.all();
	}
	
}
