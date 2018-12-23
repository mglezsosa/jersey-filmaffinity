package tech.sosa.ingweb.infrastructure.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import tech.sosa.ingweb.application.actor.service.ActorAssembler;
import tech.sosa.ingweb.application.director.service.DirectorAssembler;
import tech.sosa.ingweb.application.movie.service.MovieAssembler;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.infrastructure.persistence.actor.MySQLActorRepository;
import tech.sosa.ingweb.infrastructure.persistence.director.MySQLDirectorRepository;
import tech.sosa.ingweb.infrastructure.persistence.movie.MySQLMovieRepository;

public class DependencyBinder extends AbstractBinder {

	@Override
	protected void configure() {
		System.out.println("Loading binder...");
		
		System.out.println(System.getenv("FILMAFFINITY_DB_URL"));
		System.out.println(System.getenv("FILMAFFINITY_DB_USER"));
		System.out.println(System.getenv("FILMAFFINITY_DB_PASS"));
		
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
				System.getenv("FILMAFFINITY_DB_URL"),
				System.getenv("FILMAFFINITY_DB_USER"),
				System.getenv("FILMAFFINITY_DB_PASS")
			);
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		MovieRepository movieRepository = new MySQLMovieRepository(connection);
		ActorRepository actorRepository = new MySQLActorRepository(connection);
		DirectorRepository directorRepository = new MySQLDirectorRepository(connection);
		
		bind(movieRepository).to(MovieRepository.class);
		bind(actorRepository).to(ActorRepository.class);
		bind(directorRepository).to(DirectorRepository.class);
		
		bindAsContract(MovieAssembler.class);
		bindAsContract(ActorAssembler.class);
		bindAsContract(DirectorAssembler.class);
	}

}
