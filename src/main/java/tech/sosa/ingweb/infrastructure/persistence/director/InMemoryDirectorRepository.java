package tech.sosa.ingweb.infrastructure.persistence.director;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.MovieRepository;

public class InMemoryDirectorRepository implements DirectorRepository {
	
	private Map<DirectorId, Director> directors;
	private MovieRepository movieRepo;
	
	public InMemoryDirectorRepository() {
		this.directors = new HashMap<>();
	}
	
	public InMemoryDirectorRepository(MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}
	
	public void setMovieRepository(MovieRepository repository) {
		movieRepo = repository;
	}

	@Override
	public DirectorId nextIdentity() {
		Long newIdentityValue = Long.valueOf(directors.size() + 1);
		return new DirectorId(newIdentityValue);
	}

	@Override
	public void add(Director aDirector) {
		directors.put(aDirector.id(), aDirector);
	}

	@Override
	public void update(Director aDirector) {
		directors.put(aDirector.id(), aDirector);
	}

	@Override
	public void delete(Director aDirector) {
		directors.remove(aDirector.id());
		updateMovies(aDirector);
	}

	private void updateMovies(Director aDirector) {
		if (movieRepo == null) return;
		movieRepo.all().stream()
		.filter(m -> m.getDirector().equals(aDirector))
		.forEach(m -> m.assignDirector(null));
	}

	@Override
	public Collection<Director> all() {
		return new ArrayList<Director>(directors.values());
	}

	@Override
	public Director ofId(DirectorId directorId) {
		return directors.get(directorId);
	}

}
