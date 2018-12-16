package tech.sosa.ingweb.application.actor.service;

import javax.ws.rs.PathParam;

public class UpdateActorRequest {

	@PathParam("id")
	public long id;
	public String fullName;
	
	public UpdateActorRequest(long id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public UpdateActorRequest() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "UpdateActorRequest [id=" + id + ", fullName=" + fullName + "]";
	}
	
}
