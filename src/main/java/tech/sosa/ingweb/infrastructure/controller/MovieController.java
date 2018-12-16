package tech.sosa.ingweb.infrastructure.controller;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tech.sosa.ingweb.application.movie.service.AddMovie;
import tech.sosa.ingweb.application.movie.service.AddMovieRequest;
import tech.sosa.ingweb.application.movie.service.DeleteMovie;
import tech.sosa.ingweb.application.movie.service.DeleteMovieRequest;
import tech.sosa.ingweb.application.movie.service.ListMovie;
import tech.sosa.ingweb.application.movie.service.ListMovieRequest;
import tech.sosa.ingweb.application.movie.service.MovieAssembler;
import tech.sosa.ingweb.application.movie.service.MovieDTO;
import tech.sosa.ingweb.application.movie.service.SearchMovies;
import tech.sosa.ingweb.application.movie.service.SearchMoviesRequest;
import tech.sosa.ingweb.application.movie.service.UpdateMovie;
import tech.sosa.ingweb.application.movie.service.UpdateMovieRequest;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;

@Path("/movies")
public class MovieController {
	
	private MovieRepository movieRepository;
	private MovieAssembler assembler;
	
	@Inject
	public MovieController(MovieRepository movieRepository, MovieAssembler assembler) {
		this.movieRepository = movieRepository;
		this.assembler = assembler;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MovieDTO getMovieById(@PathParam("id") final int id) {
		Movie requestedMovie = new ListMovie(movieRepository).execute(new ListMovieRequest(id));
		return assembler.toDTO(requestedMovie);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<MovieDTO> searchMovies(@BeanParam final SearchMoviesRequest request) {
		Collection<Movie> requestedMovies = new SearchMovies(movieRepository).execute(request);
		return requestedMovies.stream().map(assembler::toDTO).collect(toList());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMovie(final AddMovieRequest request) {
		new AddMovie(movieRepository).execute(request);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMovie(@PathParam("id") final long id) {
		new DeleteMovie(movieRepository).execute(new DeleteMovieRequest(id));
		return Response.ok().build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMovie(final UpdateMovieRequest request) {
		System.out.println(request);
		new UpdateMovie(movieRepository).execute(request);
		return Response.ok().build();
	}
	
}
