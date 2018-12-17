package tech.sosa.ingweb.application.movie.service;

public class AssignActorRequest {

	public long actorId;
	public long movieId;
	
	public AssignActorRequest(long actorId, long movieId) {
		this.actorId = actorId;
		this.movieId = movieId;
	}
	@Override
	public String toString() {
		return "AssignActorRequest [actorId=" + actorId + ", movieId=" + movieId + "]";
	}
	
}
