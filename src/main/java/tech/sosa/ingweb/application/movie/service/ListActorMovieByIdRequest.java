package tech.sosa.ingweb.application.movie.service;

public class ListActorMovieByIdRequest {

	public long actorId;
	public long movieId;
	
	public ListActorMovieByIdRequest(long actorId, long movieId) {
		this.actorId = actorId;
		this.movieId = movieId;
	}

	@Override
	public String toString() {
		return "ListActorMovieByIdRequest [actorId=" + actorId + ", movieId=" + movieId + "]";
	}
	
}
