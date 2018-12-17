package tech.sosa.ingweb.application.actor.service;

public class ListMovieActorByIdRequest {

	public long movieId;
	
	public long actorId;
	
	public ListMovieActorByIdRequest(long movieId, long actorId) {
		this.movieId = movieId;
		this.actorId = actorId;
	}

	@Override
	public String toString() {
		return "ListMovieActorByIdRequest [movieId=" + movieId + ", actorId=" + actorId + "]";
	}
	
}
