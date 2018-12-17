package tech.sosa.ingweb.application.movie.service;

public class ListActorMoviesRequest {

	public long actorId;

	public ListActorMoviesRequest(long actorId) {
		super();
		this.actorId = actorId;
	}

	@Override
	public String toString() {
		return "ListActorMoviesRequest [actorId=" + actorId + "]";
	}
	
}
