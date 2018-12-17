package tech.sosa.ingweb.application.actor.service;

public class AssignDirectorRequest {

	public long movieId;
	public long directorId;
	
	public AssignDirectorRequest(long movieId, long directorId) {
		this.movieId = movieId;
		this.directorId = directorId;
	}
	
	@Override
	public String toString() {
		return "AssignDirectorRequest [movieId=" + movieId + ", directorId=" + directorId + "]";
	}
	
}
