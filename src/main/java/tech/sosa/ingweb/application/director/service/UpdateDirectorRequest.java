package tech.sosa.ingweb.application.director.service;

import javax.ws.rs.PathParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpdateDirectorRequest {

	@PathParam("id")
	public long id;
	
	public String fullName;
	
	public UpdateDirectorRequest(long id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public UpdateDirectorRequest() {
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
		return "UpdateDirectorRequest [id=" + id + ", fullName=" + fullName + "]";
	}

}
