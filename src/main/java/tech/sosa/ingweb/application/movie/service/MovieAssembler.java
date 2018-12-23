package tech.sosa.ingweb.application.movie.service;

import tech.sosa.ingweb.application.shared.Assembler;
import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieTitle;
import tech.sosa.ingweb.domain.movie.Year;

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

	@Override
	public Movie toEntity(MovieDTO dto) {
		return new Movie(new MovieId(dto.id), new MovieTitle(dto.title), new Genre(dto.genre), new Year(dto.year));
	}
	
}
