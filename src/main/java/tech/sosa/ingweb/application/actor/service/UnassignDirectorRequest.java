package tech.sosa.ingweb.application.actor.service;

public class UnassignDirectorRequest {

	public long movieId;
	public long directorId;
	
	public UnassignDirectorRequest(long movieId, long directorId) {
		super();
		this.movieId = movieId;
		this.directorId = directorId;
	}
	
	@Override
	public String toString() {
		return "UnassignDirectorRequest [movieId=" + movieId + ", directorId=" + directorId + "]";
	}

}
