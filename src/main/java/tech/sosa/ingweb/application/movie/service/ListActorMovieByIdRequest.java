package tech.sosa.ingweb.application.movie.service;

import javax.ws.rs.PathParam;

public class ListActorMovieByIdRequest {

	@PathParam("actorId")
	public long actorId;
	
	@PathParam("movieId")
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
