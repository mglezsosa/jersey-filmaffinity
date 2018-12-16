package tech.sosa.ingweb.infrastructure.controller;

import java.util.Collection;
import static java.util.stream.Collectors.toList;

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
import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorRepository;


@Path("/actors")
@Api(value = "actors")
public class ActorController {

	private ActorRepository actorRepository;
	private ActorAssembler assembler;
	
	@Inject
	public ActorController(ActorRepository actorRepository, ActorAssembler assembler) {
		this.actorRepository = actorRepository;
		this.assembler = assembler;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActorDTO getActorById(
			@ApiParam(value = "Unique identifier of the actor.", required = true) 
			@PathParam("id") final int id) {
		Actor requestedActor = new ListActorById(actorRepository).execute(new ListActorByIdRequest(id));
		return assembler.toDTO(requestedActor);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ActorDTO> searchActors(@BeanParam final SearchActorsRequest request) {
		Collection<Actor> requestedActors = new SearchActors(actorRepository).execute(request);
		return requestedActors.stream().map(assembler::toDTO).collect(toList());
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
	public Response deleteActor(final DeleteActorRequest request) {
		new DeleteActor(actorRepository).execute(request);
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateActor(final UpdateActorRequest request) {
		new UpdateActor(actorRepository).execute(request);
		return Response.ok().build();
	}
}
