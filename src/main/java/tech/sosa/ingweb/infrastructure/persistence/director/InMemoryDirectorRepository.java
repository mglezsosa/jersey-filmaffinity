package tech.sosa.ingweb.infrastructure.persistence.director;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;

public class InMemoryDirectorRepository implements DirectorRepository {
	
	private Map<DirectorId, Director> directors;
	
	public InMemoryDirectorRepository() {
		this.directors = new HashMap<>();
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
