package tech.sosa.ingweb.application.assembler;

import tech.sosa.ingweb.application.dto.MovieDTO;
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
