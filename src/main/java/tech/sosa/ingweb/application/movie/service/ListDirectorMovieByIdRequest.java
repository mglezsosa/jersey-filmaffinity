package tech.sosa.ingweb.application.movie.service;

public class ListDirectorMovieByIdRequest {

	public long directorId;
	public long movieId;
	
	public ListDirectorMovieByIdRequest(long directorId, long movieId) {
		this.directorId = directorId;
		this.movieId = movieId;
	}
	
	@Override
	public String toString() {
		return "ListMovieDirectorByIdRequest [directorId=" + directorId + ", movieId=" + movieId + "]";
	}
	
}
