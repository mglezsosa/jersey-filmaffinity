package tech.sosa.ingweb.application.director.service;

import tech.sosa.ingweb.application.shared.Assembler;
import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorId;

public class DirectorAssembler implements Assembler<Director, DirectorDTO> {

	@Override
	public DirectorDTO toDTO(Director entity) {
		if (entity == null) return null;
		return new DirectorDTO(entity.id().value(), entity.fullName().toString());
	}

	@Override
	public Director toEntity(DirectorDTO dto) {
		if (dto == null) return null;
		return new Director(new DirectorId(dto.id), new DirectorFullName(dto.fullName));
	}

}
