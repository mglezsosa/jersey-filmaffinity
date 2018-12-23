package tech.sosa.ingweb.infrastructure.persistence.actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import tech.sosa.ingweb.application.actor.service.ActorAssembler;
import tech.sosa.ingweb.application.actor.service.ActorDTO;
import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.actor.ActorFullName;
import tech.sosa.ingweb.domain.actor.ActorId;
import tech.sosa.ingweb.domain.actor.ActorRepository;

public class MySQLActorRepository implements ActorRepository {

	protected final static String ACTOR_TABLE = "Actor";
	
	protected final static String NEXT_ID = String.format("select max(id) + 1 as NEXT_ID from %s;", ACTOR_TABLE);
	protected final static String INSERT = String.format("INSERT INTO %s (id, fullname) VALUES (?, ?);", ACTOR_TABLE);
	protected final static String UPDATE = String.format("UPDATE %s SET fullname = ? WHERE id = ?;", ACTOR_TABLE);
	protected final static String DELETE = String.format("DELETE FROM %s WHERE id = ?;", ACTOR_TABLE);
	protected final static String SELECT_ALL = String.format("SELECT * FROM %s", ACTOR_TABLE);
	protected final static String SELECT_OF_ID = String.format("SELECT * FROM %s WHERE id = ?;", ACTOR_TABLE);
	
	protected Connection connection;
	protected ActorAssembler actorAssembler;
	
	public MySQLActorRepository(Connection connection) {
		this.connection = connection;
		this.actorAssembler = new ActorAssembler();
	}

	@Override
	public ActorId nextIdentity() {
		
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
		
		return new ActorId(nextLongValue);
	}

	@Override
	public void add(Actor anActor) {
		ActorDTO actorDTO = actorAssembler.toDTO(anActor);
		try (
			PreparedStatement statement = connection.prepareStatement(INSERT);
		) {
			statement.setLong(1, actorDTO.id);
			statement.setString(2, actorDTO.fullName);
			
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Actor anActor) {
		ActorDTO actorDTO = actorAssembler.toDTO(anActor);
		try (
			PreparedStatement statement = connection.prepareStatement(UPDATE);
		) {
			statement.setString(1, actorDTO.fullName);
			statement.setLong(2, actorDTO.id);
			
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Actor anActor) {
		ActorDTO actorDTO = actorAssembler.toDTO(anActor);
		try (
			PreparedStatement statement = connection.prepareStatement(DELETE);
		) {
			statement.setLong(1, actorDTO.id);
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Collection<Actor> all() {
		Collection<Actor> actors = new ArrayList<>();
		try (
				Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery(SELECT_ALL);
		) {
			
			while(results.next()) {
				ActorDTO actorDTO = new ActorDTO(results.getLong("id"), results.getString("fullname"));
				Actor currentActor = actorAssembler.toEntity(actorDTO);
				actors.add(currentActor);
			}
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return actors;
	}

	@Override
	public Actor ofId(ActorId expectedActorId) {
		Actor requestedActor;
		try (
			PreparedStatement statement = connection.prepareStatement(SELECT_OF_ID);
		) {
			statement.setLong(1, expectedActorId.value());
			ResultSet result = statement.executeQuery();
			
			result.next();
			
			requestedActor = new Actor(
					new ActorId(result.getLong("id")), 
					new ActorFullName(result.getString("fullname")));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return requestedActor ;
	}

	@Override
	public Collection<Actor> ofSpecs(String partialName) {
		String query = SELECT_ALL;
		if (partialName != null) {
			query += String.format(" WHERE title LIKE '%%s%'", partialName);
		}
		Collection<Actor> requestedActors = new ArrayList<>();
		try (
			Statement statement = connection.createStatement();
		) {
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				Actor currentActor = new Actor(
						new ActorId(result.getLong("id")), 
						new ActorFullName(result.getString("fullname")));
				requestedActors.add(currentActor);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return requestedActors;
	}

}
