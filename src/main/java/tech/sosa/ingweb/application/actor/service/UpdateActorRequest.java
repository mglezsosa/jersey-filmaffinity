package tech.sosa.ingweb.application.actor.service;

public class UpdateActorRequest {

	public long id;
	public String fullName;
	
	public UpdateActorRequest(long id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}
	
}
