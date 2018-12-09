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
	
}
