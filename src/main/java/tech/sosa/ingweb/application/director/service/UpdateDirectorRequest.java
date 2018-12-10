package tech.sosa.ingweb.application.director.service;

public class UpdateDirectorRequest {

	public long id;
	public String fullName;
	
	public UpdateDirectorRequest(long id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}
	
}
