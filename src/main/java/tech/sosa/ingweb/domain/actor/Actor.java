package tech.sosa.ingweb.domain.actor;

public class Actor {

	private ActorId id;
	private ActorFullName fullName;
	
	public Actor(ActorId id, ActorFullName fullName) {
		this.id = id;
		this.fullName = fullName;
	}
	
	public ActorId id() {
		return new ActorId(id.value());
	}
	
	public ActorFullName fullName() {
		return new ActorFullName(fullName.toString());
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", fullName=" + fullName + "]";
	}
	
}
