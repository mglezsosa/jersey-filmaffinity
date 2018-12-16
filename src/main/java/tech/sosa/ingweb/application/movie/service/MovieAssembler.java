package tech.sosa.ingweb.application.movie.service;

import tech.sosa.ingweb.application.shared.Assembler;
import tech.sosa.ingweb.domain.movie.Movie;

public class MovieAssembler implements Assembler<Movie, MovieDTO> {

	@Override
	public MovieDTO toDTO(Movie entity) {
		return new MovieDTO(
				entity.id().value(),
				entity.title().toString(),
				entity.genre().toString(),
				entity.year().value()
			);
	}
	
}
