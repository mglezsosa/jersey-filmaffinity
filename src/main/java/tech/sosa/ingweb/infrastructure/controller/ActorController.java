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
import tech.sosa.ingweb.application.actor.service.ActorAssembler;
import tech.sosa.ingweb.application.actor.service.ActorDTO;
import tech.sosa.ingweb.application.actor.service.AddActor;
import tech.sosa.ingweb.application.actor.service.AddActorRequest;
import tech.sosa.ingweb.application.actor.service.DeleteActor;
import tech.sosa.ingweb.application.actor.service.DeleteActorRequest;
import tech.sosa.ingweb.application.actor.service.ListActorById;
import tech.sosa.ingweb.application.actor.service.ListActorByIdRequest;
import tech.sosa.ingweb.application.actor.service.SearchActors;
import tech.sosa.ingweb.application.actor.service.SearchActorsRequest;
import tech.sosa.ingweb.application.actor.service.UpdateActor;
import tech.sosa.ingweb.application.actor.service.UpdateActorRequest;
import tech.sosa.ingweb.application.movie.service.ListActorMovieById;
import tech.sosa.ingweb.application.movie.service.ListActorMovieByIdRequest;
import tech.sosa.ingweb.application.movie.service.ListActorMovies;
import tech.sosa.ingweb.application.movie.service.ListActorMoviesRequest;
import tech.sosa.ingweb.application.movie.service.MovieAssembler;
import tech.sosa.ingweb.application.movie.service.MovieDTO;
import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorRepository;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieRepository;


@Path("/actors")
@Api(value = "actors")
public class ActorController {

	private ActorRepository actorRepository;
	private MovieRepository movieRepository;
	private ActorAssembler actorAssembler;
	private MovieAssembler movieAssembler;
	
	@Inject
	public ActorController(ActorRepository actorRepository, MovieRepository movieRepository,
			ActorAssembler actorAssembler, MovieAssembler movieAssembler) {
		this.actorRepository = actorRepository;
		this.movieRepository = movieRepository;
		this.actorAssembler = actorAssembler;
		this.movieAssembler = movieAssembler;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActorDTO getActorById(
			@ApiParam(value = "Unique identifier of the actor.", required = true) @PathParam("id") final long id) {
		Actor requestedActor = new ListActorById(actorRepository).execute(new ListActorByIdRequest(id));
		return actorAssembler.toDTO(requestedActor);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ActorDTO> searchActors(@BeanParam final SearchActorsRequest request) {
		Collection<Actor> requestedActors = new SearchActors(actorRepository).execute(request);
		return requestedActors.stream().map(actorAssembler::toDTO).collect(toList());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addActor(final AddActorRequest request) {
		new AddActor(actorRepository).execute(request);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteActor(@PathParam("id") final long id) {
		new DeleteActor(actorRepository).execute(new DeleteActorRequest(id));
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateActor(final UpdateActorRequest request) {
		new UpdateActor(actorRepository).execute(request);
		return Response.ok().build();
	}
	
	@GET
	@Path("/{id}/movies")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<MovieDTO> getActorMovies(@PathParam("id") final long id) {
		Collection<Movie> requestedMovies = new ListActorMovies(movieRepository, actorRepository)
				.execute(new ListActorMoviesRequest(id));
		return requestedMovies.stream().map(movieAssembler::toDTO).collect(toList());
	}
	
	@GET
	@Path("/{actorId}/movies/{movieId}")
	@Produces(MediaType.APPLICATION_JSON)
	public MovieDTO getActorMovieByid(
			@PathParam("actorId") final long actorId, 
			@PathParam("movieId") final long movieId) {
		
		Movie requestedMovie = new ListActorMovieById(movieRepository, actorRepository)
				.execute(new ListActorMovieByIdRequest(actorId, movieId));
		return movieAssembler.toDTO(requestedMovie);
	}
	
}
