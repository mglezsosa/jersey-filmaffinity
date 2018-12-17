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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import tech.sosa.ingweb.application.director.service.AddDirector;
import tech.sosa.ingweb.application.director.service.AddDirectorRequest;
import tech.sosa.ingweb.application.director.service.DeleteDirector;
import tech.sosa.ingweb.application.director.service.DeleteDirectorRequest;
import tech.sosa.ingweb.application.director.service.DirectorAssembler;
import tech.sosa.ingweb.application.director.service.DirectorDTO;
import tech.sosa.ingweb.application.director.service.ListDirector;
import tech.sosa.ingweb.application.director.service.ListDirectorRequest;
import tech.sosa.ingweb.application.director.service.SearchDirectors;
import tech.sosa.ingweb.application.director.service.SearchDirectorsRequest;
import tech.sosa.ingweb.application.director.service.UpdateDirector;
import tech.sosa.ingweb.application.director.service.UpdateDirectorRequest;
import tech.sosa.ingweb.application.movie.service.ListDirectorMovieById;
import tech.sosa.ingweb.application.movie.service.ListDirectorMovieByIdRequest;
import tech.sosa.ingweb.application.movie.service.ListDirectorMovies;
import tech.sosa.ingweb.application.movie.service.ListDirectorMoviesRequest;
import tech.sosa.ingweb.application.movie.service.MovieAssembler;
import tech.sosa.ingweb.application.movie.service.MovieDTO;
import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;

@Path("/directors")
@Api(value = "directors")
public class DirectorController {

	private DirectorRepository directorRepository;
	private MovieRepository movieRepository;
	private DirectorAssembler directorAssembler;
	private MovieAssembler movieAssembler;
	
	@Inject
	public DirectorController(DirectorRepository directorRepository, MovieRepository movieRepository,
			DirectorAssembler directorAssembler, MovieAssembler movieAssembler) {
		this.directorRepository = directorRepository;
		this.movieRepository = movieRepository;
		this.directorAssembler = directorAssembler;
		this.movieAssembler = movieAssembler;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DirectorDTO getDirectorById(
			@ApiParam(value = "Unique identifier of the director.", required = true) 
			@PathParam("id") final int id) {
		Director requestedDirector = new ListDirector(directorRepository).execute(new ListDirectorRequest(id));
		return directorAssembler.toDTO(requestedDirector);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<DirectorDTO> searchDirectors(@BeanParam final SearchDirectorsRequest request) {
		Collection<Director> requestedDirectors = new SearchDirectors(directorRepository).execute(request);
		return requestedDirectors.stream().map(directorAssembler::toDTO).collect(toList());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addDirector(final AddDirectorRequest request) {
		new AddDirector(directorRepository).execute(request);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDirector(@PathParam("id") final long id) {
		new DeleteDirector(directorRepository).execute(new DeleteDirectorRequest(id));
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDirector(final UpdateDirectorRequest request) {
		new UpdateDirector(directorRepository).execute(request);
		return Response.ok().build();
	}
	
	@GET
	@Path("/{id}/movies")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<MovieDTO> getDirectorMovies(@PathParam("id") final long id) {
		Collection<Movie> requestedMovies = new ListDirectorMovies(movieRepository, directorRepository)
				.execute(new ListDirectorMoviesRequest(id));
		return requestedMovies.stream().map(movieAssembler::toDTO).collect(toList());
	}
	
	@GET
	@Path("/{directorId}/movies/{movieId}")
	@Produces(MediaType.APPLICATION_JSON)
	public MovieDTO getDirectorMovieByid(
			@PathParam("directorId") final long directorId, 
			@PathParam("movieId") final long movieId) {
		
		Movie requestedMovie = new ListDirectorMovieById(movieRepository, directorRepository)
				.execute(new ListDirectorMovieByIdRequest(directorId, movieId));
		return movieAssembler.toDTO(requestedMovie);
	}

}
