package tech.sosa.ingweb.application.actor.service;

import tech.sosa.ingweb.application.shared.Assembler;
import tech.sosa.ingweb.domain.actor.Actor;

public class ActorAssembler implements Assembler<Actor, ActorDTO> {

	@Override
	public ActorDTO toDTO(Actor entity) {
		return new ActorDTO(entity.id().value(), entity.fullName().toString());
	}

}
