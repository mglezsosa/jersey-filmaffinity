package tech.sosa.ingweb.domain.movie;

import java.util.Collection;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.director.Director;

public interface MovieRepository {
	
	public MovieId nextIdentity();

	public void add(Movie aMovie);

	public void update(Movie aMovie);

	public void delete(Movie aMovie);

	public Collection<Movie> all();

	public Movie ofId(MovieId expectedMovieId);

	public Collection<Movie> ofDirector(Director director);
	
	public Collection<Movie> ofActor(Actor actor);
	
}
