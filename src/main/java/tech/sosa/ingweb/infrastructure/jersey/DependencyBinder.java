package tech.sosa.ingweb.infrastructure.jersey;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import tech.sosa.ingweb.application.actor.service.ActorAssembler;
import tech.sosa.ingweb.application.director.service.DirectorAssembler;
import tech.sosa.ingweb.application.movie.service.MovieAssembler;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.infrastructure.persistence.actor.ActorRepositoryStub;
import tech.sosa.ingweb.infrastructure.persistence.director.DirectorRepositoryStub;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class DependencyBinder extends AbstractBinder {

	@Override
	protected void configure() {
		System.out.println("Loading binder...");
		
		MovieRepository movieRepository = MovieRepositoryStub.withDummyData();
		ActorRepository actorRepository = ActorRepositoryStub.withDummyData();
		DirectorRepository directorRepository = DirectorRepositoryStub.withDummyData();
		
		bind(movieRepository).to(MovieRepository.class);
		bind(actorRepository).to(ActorRepository.class);
		bind(directorRepository).to(DirectorRepository.class);
		
		bindAsContract(MovieAssembler.class);
		bindAsContract(ActorAssembler.class);
		bindAsContract(DirectorAssembler.class);
	}

}
