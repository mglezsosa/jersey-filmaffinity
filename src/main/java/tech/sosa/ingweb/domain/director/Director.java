package tech.sosa.ingweb.domain.director;

public class Director {

	private DirectorId id;
	private DirectorFullName fullName;
	
	public Director(DirectorId id, DirectorFullName fullName) {
		this.id = id;
		this.fullName = fullName;
	}
	
	public DirectorId id() {
		return new DirectorId(id.value());
	}
	
	public DirectorFullName fullName() {
		return new DirectorFullName(fullName.toString());
	}

	@Override
	public String toString() {
		return "Director [id=" + id + ", fullName=" + fullName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Director other = (Director) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
