package tech.sosa.ingweb.application.director.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DirectorDTO {

	public long id;
	public String fullName;
	
	public DirectorDTO(long id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}
	
}
