package tech.sosa.ingweb.application.actor.service;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchActorsRequest {
	
	@QueryParam("name_portion")
	public String partialName;

	public SearchActorsRequest(String partialFullName) {
		this.partialName = partialFullName;
	}

	public SearchActorsRequest() {
	}

	public String getPartialName() {
		return partialName;
	}

	public void setPartialName(String partialName) {
		this.partialName = partialName;
	}

	@Override
	public String toString() {
		return "SearchActorsRequest [partialName=" + partialName + "]";
	}
	
	public boolean isEmpty() {
		if (partialName == null || partialName == "") {
			return true;
		}
		return false;
	}
	
}
