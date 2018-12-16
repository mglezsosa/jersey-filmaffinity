package tech.sosa.ingweb.application.director.service;

public class AddDirectorRequest {

	public String fullName;

	public AddDirectorRequest(String fullName) {
		this.fullName = fullName;
	}

	public AddDirectorRequest() {
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "AddDirectorRequest [fullName=" + fullName + "]";
	}

}
