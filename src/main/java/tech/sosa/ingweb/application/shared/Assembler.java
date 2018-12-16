package tech.sosa.ingweb.application.shared;

public interface Assembler<T, TDTO> {

	public TDTO toDTO(T entity);
	
}
