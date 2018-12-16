package tech.sosa.ingweb.infrastructure.jersey;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import tech.sosa.ingweb.application.movie.service.MovieAssembler;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.infrastructure.persistence.movie.MovieRepositoryStub;

public class DependencyBinder extends AbstractBinder {

	@Override
	protected void configure() {
		System.out.println("Loading binder...");
		
		MovieRepository movieRepository = MovieRepositoryStub.withDummyData();
		
		bind(movieRepository).to(MovieRepository.class);
		bindAsContract(MovieAssembler.class);
	}

}
