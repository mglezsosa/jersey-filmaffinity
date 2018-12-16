package tech.sosa.ingweb.application.director.service;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchDirectorsRequest {

	@QueryParam("name_portion")
	public String partialName;

	public SearchDirectorsRequest(String partialName) {
		this.partialName = partialName;
	}

	public SearchDirectorsRequest() {
	}

	public String getPartialName() {
		return partialName;
	}

	public void setPartialName(String partialName) {
		this.partialName = partialName;
	}

	@Override
	public String toString() {
		return "SearchDirectorsRequest [partialName=" + partialName + "]";
	}
	
	public boolean isEmpty() {
		if (partialName == null || partialName == "") return true;
		return false;
	}
}
