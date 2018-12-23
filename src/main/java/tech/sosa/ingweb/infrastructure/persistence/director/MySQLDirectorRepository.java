package tech.sosa.ingweb.infrastructure.persistence.director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import tech.sosa.ingweb.application.director.service.DirectorAssembler;
import tech.sosa.ingweb.application.director.service.DirectorDTO;
import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.director.DirectorFullName;
import tech.sosa.ingweb.domain.director.DirectorId;
import tech.sosa.ingweb.domain.director.DirectorRepository;

public class MySQLDirectorRepository implements DirectorRepository {

	protected final static String DIRECTOR_TABLE = "Director";
	
	protected final static String NEXT_ID = String.format("select max(id) + 1 as NEXT_ID from %s;", DIRECTOR_TABLE);
	protected final static String INSERT = String.format("INSERT INTO %s (id, fullname) VALUES (?, ?);", DIRECTOR_TABLE);
	protected final static String UPDATE = String.format("UPDATE %s SET fullname = ? WHERE id = ?;", DIRECTOR_TABLE);
	protected final static String DELETE = String.format("DELETE FROM %s WHERE id = ?;", DIRECTOR_TABLE);
	protected final static String SELECT_ALL = String.format("SELECT * FROM %s", DIRECTOR_TABLE);
	protected final static String SELECT_OF_ID = String.format("SELECT * FROM %s WHERE id = ?;", DIRECTOR_TABLE);
	
	protected Connection connection;
	protected DirectorAssembler directorAssembler;
	
	public MySQLDirectorRepository(Connection connection) {
		this.connection = connection;
		this.directorAssembler = new DirectorAssembler();
	}

	@Override
	public DirectorId nextIdentity() {
		Long nextLongValue = null;
		try (
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(NEXT_ID);
		){
			rs.next();
			nextLongValue = rs.getLong("NEXT_ID");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return new DirectorId(nextLongValue);
	}

	@Override
	public void add(Director aDirector) {
		DirectorDTO directorDTO = directorAssembler.toDTO(aDirector);
		try (
			PreparedStatement statement = connection.prepareStatement(INSERT);
		) {
			statement.setLong(1, directorDTO.id);
			statement.setString(2, directorDTO.fullName);
			
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Director aDirector) {
		DirectorDTO directorDTO = directorAssembler.toDTO(aDirector);
		try (
			PreparedStatement statement = connection.prepareStatement(UPDATE);
		) {
			statement.setString(1, directorDTO.fullName);
			statement.setLong(2, directorDTO.id);
			
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Director aDirector) {
		DirectorDTO directorDTO = directorAssembler.toDTO(aDirector);
		try (
			PreparedStatement statement = connection.prepareStatement(DELETE);
		) {
			statement.setLong(1, directorDTO.id);
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Collection<Director> all() {
		Collection<Director> directors = new ArrayList<>();
		try (
				Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery(SELECT_ALL);
		) {
			
			while(results.next()) {
				DirectorDTO directorDTO = new DirectorDTO(results.getLong("id"), results.getString("fullname"));
				Director currentDirector = directorAssembler.toEntity(directorDTO);
				directors.add(currentDirector);
			}
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return directors;
	}

	@Override
	public Director ofId(DirectorId expectedDirectorId) {
		Director requestedDirector;
		try (
			PreparedStatement statement = connection.prepareStatement(SELECT_OF_ID);
		) {
			statement.setLong(1, expectedDirectorId.value());
			ResultSet result = statement.executeQuery();
			
			result.next();
			
			requestedDirector = new Director(
					new DirectorId(result.getLong("id")), 
					new DirectorFullName(result.getString("fullname")));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return requestedDirector;
	}

	@Override
	public Collection<Director> ofSpecs(String partialName) {
		String query = SELECT_ALL;
		if (partialName != null) {
			query += String.format(" WHERE title LIKE '%%s%'", partialName);
		}
		Collection<Director> requestedDirectors = new ArrayList<>();
		try (
			Statement statement = connection.createStatement();
		) {
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				Director currentDirector = new Director(
						new DirectorId(result.getLong("id")), 
						new DirectorFullName(result.getString("fullname")));
				requestedDirectors.add(currentDirector);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return requestedDirectors;
	}

}
