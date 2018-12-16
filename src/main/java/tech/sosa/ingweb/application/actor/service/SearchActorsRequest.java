package tech.sosa.ingweb.application.actor.service;

public class SearchActorsRequest {
	
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
