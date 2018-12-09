package tech.sosa.ingweb.domain.movie;

public class MovieTitle {

	private String titleString;

	public MovieTitle(String titleString) {
		this.titleString = titleString;
	}

	@Override
	public String toString() {
		return titleString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titleString == null) ? 0 : titleString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieTitle other = (MovieTitle) obj;
		if (titleString == null) {
			if (other.titleString != null)
				return false;
		} else if (!titleString.equals(other.titleString))
			return false;
		return true;
	}
	
}
