package tech.sosa.ingweb.application.actor.service;

import tech.sosa.ingweb.application.shared.Assembler;
import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorFullName;
import tech.sosa.ingweb.domain.actor.ActorId;

public class ActorAssembler implements Assembler<Actor, ActorDTO> {

	@Override
	public ActorDTO toDTO(Actor entity) {
		return new ActorDTO(entity.id().value(), entity.fullName().toString());
	}

	@Override
	public Actor toEntity(ActorDTO dto) {
		if (dto == null) return null;
		return new Actor(new ActorId(dto.id), new ActorFullName(dto.fullName));
	}
	
	

}
