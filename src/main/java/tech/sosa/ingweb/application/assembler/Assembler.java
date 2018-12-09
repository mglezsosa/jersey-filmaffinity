package tech.sosa.ingweb.application.assembler;

public interface Assembler<T, TDTO> {

	public TDTO toDTO(T entity);
	
}
