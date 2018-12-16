package tech.sosa.ingweb.application.director.service;

import tech.sosa.ingweb.application.shared.Assembler;
import tech.sosa.ingweb.domain.director.Director;

public class DirectorAssembler implements Assembler<Director, DirectorDTO> {

	@Override
	public DirectorDTO toDTO(Director entity) {
		return new DirectorDTO(entity.id().value(), entity.fullName().toString());
	}

}
