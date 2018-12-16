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
import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorRepository;

@Path("/directors")
@Api(value = "directors")
public class DirectorController {

	private DirectorRepository repository;
	private DirectorAssembler assembler;
	
	@Inject
	public DirectorController(DirectorRepository repository, DirectorAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DirectorDTO getDirectorById(
			@ApiParam(value = "Unique identifier of the director.", required = true) 
			@PathParam("id") final int id) {
		Director requestedDirector = new ListDirector(repository).execute(new ListDirectorRequest(id));
		return assembler.toDTO(requestedDirector);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<DirectorDTO> searchDirectors(@BeanParam final SearchDirectorsRequest request) {
		Collection<Director> requestedDirectors = new SearchDirectors(repository).execute(request);
		return requestedDirectors.stream().map(assembler::toDTO).collect(toList());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addDirector(final AddDirectorRequest request) {
		new AddDirector(repository).execute(request);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDirector(final DeleteDirectorRequest request) {
		new DeleteDirector(repository).execute(request);
		return Response.ok().build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDirector(final UpdateDirectorRequest request) {
		new UpdateDirector(repository).execute(request);
		return Response.ok().build();
	}
}
