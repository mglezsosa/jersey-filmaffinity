package tech.sosa.ingweb.infrastructure.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tech.sosa.ingweb.application.movie.assembler.MovieAssembler;
import tech.sosa.ingweb.application.movie.dto.MovieDTO;
import tech.sosa.ingweb.application.movie.service.ListAllMovies;
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
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<MovieDTO> getAllMovies() {
		Collection<Movie> allTheMovies = new ListAllMovies(movieRepository).execute();
		return allTheMovies.stream().map(assembler::toDTO).collect(Collectors.toList());
	}

}
