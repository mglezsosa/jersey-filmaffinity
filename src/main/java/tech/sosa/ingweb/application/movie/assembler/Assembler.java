package tech.sosa.ingweb.application.movie.assembler;

public interface Assembler<T, TDTO> {

	public TDTO toDTO(T entity);
	
}
