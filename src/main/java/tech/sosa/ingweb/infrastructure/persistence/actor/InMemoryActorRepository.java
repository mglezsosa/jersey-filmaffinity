package tech.sosa.ingweb.infrastructure.persistence.actor;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class InMemoryActorRepository implements ActorRepository {
	
	private Map<ActorId, Actor> actors;
	private MovieRepository movieRepo;

	public InMemoryActorRepository() {
		this.actors = new HashMap<>();
	}
	
	public InMemoryActorRepository(MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}
	
	public void setMovieRepository(MovieRepository repository) {
		movieRepo = repository;
	}

	@Override
	public ActorId nextIdentity() {
		Long newIdentity = Long.valueOf(actors.size() + 1);
		return new ActorId(newIdentity);
	}

	@Override
	public void add(Actor anActor) {
		actors.put(anActor.id(), anActor);
	}

	@Override
	public void update(Actor anActor) {
		actors.put(anActor.id(), anActor);
	}

	@Override
	public void delete(Actor anActor) {
		actors.remove(anActor.id());
		updateMovies(anActor);
	}

	private void updateMovies(Actor anActor) {
		if (movieRepo == null) return;
		movieRepo.all().stream()
		.filter(m -> m.getActors().contains(anActor))
		.forEach(m -> m.unassignActor(anActor));
	}

	@Override
	public Collection<Actor> all() {
		return new ArrayList<Actor>(actors.values());
	}

	@Override
	public Actor ofId(ActorId expectedActorId) {
		return actors.get(expectedActorId);
	}

	@Override
	public Collection<Actor> ofSpecs(String partialName) {
		return filterActors(partialName, all());
	}
	
	private Collection<Actor> filterActors(String partialName, Collection<Actor> actors) {
		return actors.stream()
				.filter(a -> a.fullName().toString().toLowerCase().contains(partialName.toLowerCase()))
				.collect(toList());
	}

}
