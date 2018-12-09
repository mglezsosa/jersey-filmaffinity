package tech.sosa.ingweb.application.director.service;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorRepository;

public class AddDirector {

	private DirectorRepository directorRepository;

	public AddDirector(DirectorRepository directorRepository) {
		this.directorRepository = directorRepository;
	}
	
	public void execute(AddDirectorRequest request) {
		directorRepository.add(new Director(
					directorRepository.nextIdentity(),
					new DirectorFullName(request.fullName)
				));
	}
	
}
