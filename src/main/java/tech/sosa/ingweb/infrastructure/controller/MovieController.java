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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.sosa.ingweb.application.actor.service.ActorAssembler;
import tech.sosa.ingweb.application.actor.service.ActorDTO;
import tech.sosa.ingweb.application.actor.service.AssignDirector;
import tech.sosa.ingweb.application.actor.service.AssignDirectorRequest;
import tech.sosa.ingweb.application.actor.service.ListMovieActorById;
import tech.sosa.ingweb.application.actor.service.ListMovieActorByIdRequest;
import tech.sosa.ingweb.application.actor.service.UnassignDirector;
import tech.sosa.ingweb.application.actor.service.UnassignDirectorRequest;
import tech.sosa.ingweb.application.director.service.DirectorAssembler;
import tech.sosa.ingweb.application.director.service.DirectorDTO;
import tech.sosa.ingweb.application.movie.service.AddMovie;
import tech.sosa.ingweb.application.movie.service.AddMovieRequest;
import tech.sosa.ingweb.application.movie.service.AssignActor;
import tech.sosa.ingweb.application.movie.service.AssignActorRequest;
import tech.sosa.ingweb.application.movie.service.DeleteMovie;
import tech.sosa.ingweb.application.movie.service.DeleteMovieRequest;
import tech.sosa.ingweb.application.movie.service.ListMovie;
import tech.sosa.ingweb.application.movie.service.ListMovieRequest;
import tech.sosa.ingweb.application.movie.service.MovieAssembler;
import tech.sosa.ingweb.application.movie.service.MovieDTO;
import tech.sosa.ingweb.application.movie.service.SearchMovies;
import tech.sosa.ingweb.application.movie.service.SearchMoviesRequest;
import tech.sosa.ingweb.application.movie.service.UnassignActor;
import tech.sosa.ingweb.application.movie.service.UnassignActorRequest;
import tech.sosa.ingweb.application.movie.service.UpdateMovie;
import tech.sosa.ingweb.application.movie.service.UpdateMovieRequest;
import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.director.DirectorDoesNotExistException;
import tech.sosa.ingweb.domain.director.DirectorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;

@Path("/movies")
@Api(value = "movies")
public class MovieController {
	
	private MovieRepository movieRepository;
	private DirectorRepository directorRepository;
	private ActorRepository actorRepository;
	private MovieAssembler movieAssembler;
	private ActorAssembler actorAssembler;
	private DirectorAssembler directorAssembler;
	
	@Inject
	public MovieController(MovieRepository movieRepository, DirectorRepository directorRepository,
			ActorRepository actorRepository, MovieAssembler movieAssembler, ActorAssembler actorAssembler,
			DirectorAssembler directorAssembler) {
		this.movieRepository = movieRepository;
		this.directorRepository = directorRepository;
		this.actorRepository = actorRepository;
		this.movieAssembler = movieAssembler;
		this.actorAssembler = actorAssembler;
		this.directorAssembler = directorAssembler;
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MovieDTO getMovieById (
			@ApiParam(value = "Unique identifier of the movie.", required = true) 
			@PathParam("id") final int id) {
		
		Movie requestedMovie = new ListMovie(movieRepository).execute(new ListMovieRequest(id));
		return movieAssembler.toDTO(requestedMovie);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<MovieDTO> searchMovies(@BeanParam final SearchMoviesRequest request) {
		Collection<Movie> requestedMovies = new SearchMovies(movieRepository).execute(request);
		return requestedMovies.stream().map(movieAssembler::toDTO).collect(toList());
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMovie(final UpdateMovieRequest request) {
		System.out.println(request);
		new UpdateMovie(movieRepository).execute(request);
		return Response.ok().build();
	}
	
	@POST
	@Path("/{movieId}/director/{directorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Assigns an existing director to an existing movie.", response = Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 404, message = "Movie and/or director does not exist.")
	})
	public Response assignDirector(
			@PathParam("movieId") final long movieId,
			@PathParam("directorId") final long directorId) {
		
		new AssignDirector(movieRepository, directorRepository)
			.execute(new AssignDirectorRequest(movieId, directorId));
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{movieId}/director/{directorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Unassigns a director from an existing movie.", 
	notes = "The new director will be null",
	response = Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 404, message = "Movie and/or director does not exist.")
	})
	public Response unassignDirector(
			@PathParam("movieId") final long movieId,
			@PathParam("directorId") final long directorId) {
		
		new UnassignDirector(movieRepository, directorRepository)
			.execute(new UnassignDirectorRequest(movieId, directorId));
		return Response.ok().build();
	}
	
	@POST
	@Path("/{movieId}/actors/{actorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Assigns an existing actor to an existing movie.", response = Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 404, message = "Movie and/or actor does not exist.")
	})
	public Response assignActor(
			@PathParam("actorId") final long actorId,
			@PathParam("movieId") final long movieId) {
		
		new AssignActor(movieRepository, actorRepository)
			.execute(new AssignActorRequest(actorId, movieId));
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{movieId}/actors/{actorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Unassigns an actor from an existing movie.", 
	notes = "If the actor is not assigned, nothing happens.",
	response = Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Returns this response even if the actor was not previously present in the movie."),
			@ApiResponse(code = 404, message = "Movie and/or actor does not exist.")
	})
	public Response unassignActor(
			@PathParam("actorId") final long actorId,
			@PathParam("movieId") final long movieId) {
		
		new UnassignActor(movieRepository, actorRepository)
			.execute(new UnassignActorRequest(actorId, movieId));
		return Response.ok().build();
	}
	
	@GET
	@Path("/{id}/actors")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ActorDTO> getMovieActors(@PathParam("id") final long id) {
		
		Movie requestedMovie = new ListMovie(movieRepository)
				.execute(new ListMovieRequest(id));
		return requestedMovie.getActors().stream().map(actorAssembler::toDTO).collect(toList());
	}

	@GET
	@Path("/{movieId}/actors/{actorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActorDTO getMovieActorById(
			@PathParam("movieId") final long movieId, 
			@PathParam("actorId") final long actorId) {
		
		Actor requestedActor = new ListMovieActorById(movieRepository)
				.execute(new ListMovieActorByIdRequest(movieId, actorId));
		return actorAssembler.toDTO(requestedActor);
	}
	
	@GET
	@Path("/{id}/director/")
	@Produces(MediaType.APPLICATION_JSON)
	public DirectorDTO getDirectorMovie(@PathParam("id") final long id) {
		Movie requestedMovie = new ListMovie(movieRepository).execute(new ListMovieRequest(id));
		if (requestedMovie.getDirector() == null) {
			throw new DirectorDoesNotExistException();
		}
		return directorAssembler.toDTO(requestedMovie.getDirector());
	}
	
}
