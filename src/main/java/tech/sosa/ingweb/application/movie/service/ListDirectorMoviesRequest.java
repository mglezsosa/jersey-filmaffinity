package tech.sosa.ingweb.application.movie.service;

public class ListDirectorMoviesRequest {

	public long directorId;

	public ListDirectorMoviesRequest(long directorId) {
		super();
		this.directorId = directorId;
	}

	@Override
	public String toString() {
		return "ListDirectorMoviesRequest [directorId=" + directorId + "]";
	}
	
}
