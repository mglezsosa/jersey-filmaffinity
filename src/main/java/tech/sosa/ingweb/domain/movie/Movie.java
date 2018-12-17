package tech.sosa.ingweb.domain.movie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.director.Director;

public class Movie {
	
	protected MovieId id;
	protected MovieTitle title;
	protected Genre genre;
	protected Year year;
	
	protected Director director;
	protected Map<ActorId, Actor> actors = new HashMap<>();
	
	public Movie(MovieId id, MovieTitle title, Genre genre, Year year) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
	}

	public Movie(MovieId id, MovieTitle title, Genre genre, Year year, Director director, Map<ActorId, Actor> actors) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.director = director;
		this.actors = actors;
	}

	public MovieId id() {
		return new MovieId(id.value());
	}
	
	public MovieTitle title() {
		return new MovieTitle(title.toString());
	}
	
	public Genre genre() {
		return new Genre(genre.toString());
	}
	
	public Year year() {
		return new Year(year.value());
	}
	
	public void assignDirector(Director director) {
		this.director = director;
	}
	
	public Director getDirector() {
		return director;
	}
	
	public void assignActor(Actor actor) {
		actors.put(actor.id(), actor);
	}
	
	public void unassignActor(Actor actor) {
		actors.remove(actor.id());
	}
	
	public Collection<Actor> getActors() {
		return actors.values();
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", genre=" + genre + ", year=" + year + "]";
	}
	
}
