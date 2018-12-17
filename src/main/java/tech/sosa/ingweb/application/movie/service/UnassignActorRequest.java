package tech.sosa.ingweb.application.movie.service;

public class UnassignActorRequest {

	public long actorId;
	public long movieId;
	
	public UnassignActorRequest(long actorId, long movieId) {
		this.actorId = actorId;
		this.movieId = movieId;
	}
	@Override
	public String toString() {
		return "UnassignActorRequest [actorId=" + actorId + ", movieId=" + movieId + "]";
	}
	
}
