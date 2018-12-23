package tech.sosa.ingweb.domain.director;

import java.util.Collection;

public interface DirectorRepository {
	
	public DirectorId nextIdentity();

	public void add(Director aMovie);

	public void update(Director aMovie);

	public void delete(Director aMovie);

	public Collection<Director> all();

	public Director ofId(DirectorId expectedMovieId);
	
	public Collection<Director> ofSpecs(String partialName);
}
