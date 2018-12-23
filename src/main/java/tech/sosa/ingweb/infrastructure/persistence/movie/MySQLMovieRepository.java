package tech.sosa.ingweb.infrastructure.persistence.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import tech.sosa.ingweb.application.actor.service.ActorAssembler;
import tech.sosa.ingweb.application.actor.service.ActorDTO;
import tech.sosa.ingweb.application.director.service.DirectorAssembler;
import tech.sosa.ingweb.application.director.service.DirectorDTO;
import tech.sosa.ingweb.application.movie.service.MovieAssembler;
import tech.sosa.ingweb.application.movie.service.MovieDTO;
import tech.sosa.ingweb.domain.actor.Actor;
import tech.sosa.ingweb.domain.director.Director;
import tech.sosa.ingweb.domain.movie.Genre;
import tech.sosa.ingweb.domain.movie.Movie;
import tech.sosa.ingweb.domain.movie.MovieId;
import tech.sosa.ingweb.domain.movie.MovieRepository;
import tech.sosa.ingweb.domain.movie.Year;

public class MySQLMovieRepository implements MovieRepository {
	
	protected final static String MOVIE_TABLE = "Movie";
	protected final static String DIRECTOR_TABLE = "Director";
	protected final static String ACTOR_TABLE = "Actor";
	protected final static String MOVIE_ACTOR_TABLE = "Movie_has_Actor";
	
	protected final static String NEXT_ID = String.format("select max(id) + 1 as NEXT_ID from %s;", MOVIE_TABLE);
	protected final static String INSERT = String.format("INSERT INTO %s (id, title, genre, year, Director_id)\n"
			+ "VALUES (?, ?, ?, ?, NULL);", MOVIE_TABLE);
	protected final static String UPDATE = String.format("UPDATE %s SET title = ?, genre = ?, year = ?, Director_id = ?\n"
			+ "WHERE id = ?;", MOVIE_TABLE);
	protected final static String DELETE = String.format("DELETE FROM %s WHERE id = ?;", MOVIE_TABLE);
	protected final static String SELECT_ALL = String.format("SELECT * FROM %s", MOVIE_TABLE);
	protected final static String SELECT_DIRECTOR = String.format("SELECT %s.* FROM %s INNER JOIN %s ON %s.Director_id = %s.id AND %s.id = ?;",
			DIRECTOR_TABLE,
			DIRECTOR_TABLE,
			MOVIE_TABLE,
			MOVIE_TABLE,
			DIRECTOR_TABLE,
			MOVIE_TABLE);
	protected final static String SELECT_ACTORS = String.format("SELECT %s.* FROM %s INNER JOIN %s "
			+ "ON %s.id = %s.Actor_id AND %s.Movie_id = ?;",
			ACTOR_TABLE,
			ACTOR_TABLE,
			MOVIE_ACTOR_TABLE,
			ACTOR_TABLE,
			MOVIE_ACTOR_TABLE,
			MOVIE_ACTOR_TABLE);
	protected final static String SELECT_OF_ID = String.format("SELECT * FROM %s WHERE id = ?;", MOVIE_TABLE);
	protected final static String SELECT_OF_DIRECTOR = String.format("SELECT * FROM %s WHERE Director_id = ?;", MOVIE_TABLE);
	protected final static String SELECT_OF_ACTOR = String.format(
			"SELECT %s.* FROM %s INNER JOIN %s ON %s.id = %s.Movie_id AND %s.Actor_id = ?;",
			MOVIE_TABLE,
			MOVIE_TABLE,
			MOVIE_ACTOR_TABLE,
			MOVIE_TABLE,
			MOVIE_ACTOR_TABLE,
			MOVIE_ACTOR_TABLE);
	protected final static String UNASSIGN_ACTOR = String.format("DELETE FROM %s WHERE Movie_id = ? AND Actor_id = ?;", MOVIE_ACTOR_TABLE);
	protected final static String ASSIGN_ACTOR = String.format("INSERT INTO %s (Movie_id, Actor_id) VALUES (?, ?);", MOVIE_ACTOR_TABLE);
	
	protected Connection connection;
	protected MovieAssembler movieAssembler;
	protected DirectorAssembler directorAssembler;
	protected ActorAssembler actorAssembler;
	
	public MySQLMovieRepository(Connection connection) {
		this.connection = connection;
		this.movieAssembler = new MovieAssembler();
		this.directorAssembler = new DirectorAssembler();
		this.actorAssembler = new ActorAssembler();
	}

	@Override
	public MovieId nextIdentity() {
		
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
		
		return new MovieId(nextLongValue);
	}

	@Override
	public void add(Movie aMovie) {
		MovieDTO movieDTO = movieAssembler.toDTO(aMovie);
		try (
			PreparedStatement statement = connection.prepareStatement(INSERT);
		) {
			statement.setLong(1, movieDTO.id);
			statement.setString(2, movieDTO.title);
			statement.setString(3, movieDTO.genre);
			statement.setInt(4, movieDTO.year);
			
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Movie aMovie) {
		MovieDTO movieDTO = movieAssembler.toDTO(aMovie);
		try (
			PreparedStatement statement = connection.prepareStatement(UPDATE);
		) {
			statement.setString(1, movieDTO.title);
			statement.setString(2, movieDTO.genre);
			statement.setInt(3, movieDTO.year);
			
			if (aMovie.getDirector() != null) {
				DirectorDTO itsDirectorDTO = directorAssembler.toDTO(aMovie.getDirector());
				statement.setLong(4, itsDirectorDTO.id);
			} else {
				statement.setNull(4, java.sql.Types.INTEGER);
			}
			
			statement.setLong(5, movieDTO.id);
			
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		updateAssignments(aMovie);
	}

	private void updateAssignments(Movie aMovie) {
		
		MovieDTO movieDTO = movieAssembler.toDTO(aMovie);
		Movie movieBefore = ofId(aMovie.id());
		
		Collection<Actor> actorsToBeUnassigned = new ArrayList<>(movieBefore.getActors());
		actorsToBeUnassigned.removeAll(aMovie.getActors());
		
		Collection<Actor> actorsToBeAssigned = new ArrayList<>(aMovie.getActors());
		actorsToBeAssigned.removeAll(movieBefore.getActors());
		
		actorsToBeUnassigned.stream()
		.forEach(actor -> deleteOldActorAssigments(actor, movieDTO));
		
		actorsToBeAssigned.stream()
		.forEach(actor -> insertNewActorAssigments(actor, movieDTO));
	}

	private void deleteOldActorAssigments(Actor anActor, MovieDTO movieDTO) {
			
			ActorDTO actorDTO = actorAssembler.toDTO(anActor);
			
			try (
					PreparedStatement statement = connection.prepareStatement(UNASSIGN_ACTOR);
			) {
				statement.setLong(1, movieDTO.id);
				statement.setLong(2, actorDTO.id);
				
				statement.execute();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	private void insertNewActorAssigments(Actor anActor, MovieDTO movieDTO) {
		
		ActorDTO actorDTO = actorAssembler.toDTO(anActor);
		
		try (
				PreparedStatement statement = connection.prepareStatement(ASSIGN_ACTOR);
		) {
			statement.setLong(1, movieDTO.id);
			statement.setLong(2, actorDTO.id);
			
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Movie aMovie) {
		MovieDTO movieDTO = movieAssembler.toDTO(aMovie);
		try (
			PreparedStatement statement = connection.prepareStatement(DELETE);
		) {
			statement.setLong(1, movieDTO.id);
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Collection<Movie> all() {
		Collection<Movie> movies = new ArrayList<>();
		try (
				Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery(SELECT_ALL);
		) {
			
			while(results.next()) {
				Movie currentMovie = buildMovieFromResultSet(results);
				movies.add(currentMovie);
			}
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return movies;
	}

	private Movie buildMovieFromResultSet(ResultSet results) throws SQLException {
		MovieDTO movieDTO = new MovieDTO(
				results.getLong("id"),
				results.getString("title"),
				results.getString("genre"),
				results.getInt("year"));
		
		Movie currentMovie = movieAssembler.toEntity(movieDTO);
		Director itsDirector = retrieveDirector(currentMovie);
		currentMovie.assignDirector(itsDirector);
		retrieveActors(currentMovie).stream().forEach(currentMovie::assignActor);
		return currentMovie;
	}

	private Collection<Actor> retrieveActors(Movie currentMovie) throws SQLException {
		Collection<Actor> itsMovies = new ArrayList<>();
		try (
				PreparedStatement statement2 = connection.prepareStatement(SELECT_ACTORS);
		) {
			statement2.setLong(1, currentMovie.id().value());
			ResultSet results2 = statement2.executeQuery();
			
			while(results2.next()) {
				ActorDTO currentActorDTO = new ActorDTO(results2.getLong("id"), results2.getString("fullname"));
				itsMovies.add(actorAssembler.toEntity(currentActorDTO));
			}
			results2.close();
		}
		return itsMovies;
	}

	private Director retrieveDirector(Movie currentMovie) {
		DirectorDTO itsDirectorDTO;
		try (
			PreparedStatement statement = connection.prepareStatement(SELECT_DIRECTOR);
		) {
			statement.setLong(1, currentMovie.id().value());
			ResultSet result = statement.executeQuery();
			
			if (result.next() == false) {
				result.close();
				return null;
			}
			
			itsDirectorDTO = new DirectorDTO(result.getLong("id"), result.getString("fullname"));
			result.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return directorAssembler.toEntity(itsDirectorDTO);
	}

	@Override
	public Movie ofId(MovieId expectedMovieId) {
		Movie requestedMovie;
		try (
			PreparedStatement statement = connection.prepareStatement(SELECT_OF_ID);
		) {
			statement.setLong(1, expectedMovieId.value());
			ResultSet result = statement.executeQuery();
			
			result.next();
			
			requestedMovie = buildMovieFromResultSet(result);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return requestedMovie;
	}

	@Override
	public Collection<Movie> ofDirector(Director director) {
		Collection<Movie> requestedMovies = new ArrayList<>();
		try (
			PreparedStatement statement = connection.prepareStatement(SELECT_OF_DIRECTOR);
		) {
			statement.setLong(1, director.id().value());
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Movie currentMovie = buildMovieFromResultSet(result);
				requestedMovies.add(currentMovie);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return requestedMovies;
	}

	@Override
	public Collection<Movie> ofActor(Actor actor) {
		Collection<Movie> requestedMovies = new ArrayList<>();
		try (
			PreparedStatement statement = connection.prepareStatement(SELECT_OF_ACTOR);
		) {
			statement.setLong(1, actor.id().value());
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Movie currentMovie = buildMovieFromResultSet(result);
				requestedMovies.add(currentMovie);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return requestedMovies;
	}

	@Override
	public Collection<Movie> ofSpecs(String partialTitle, Genre genre, Year year) {
		String query = buildQuery(partialTitle, genre, year);
		Collection<Movie> requestedMovies = new ArrayList<>();
		try (
			Statement statement = connection.createStatement();
		) {
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				Movie currentMovie = buildMovieFromResultSet(result);
				requestedMovies.add(currentMovie);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return requestedMovies;
	}

	private String buildQuery(String partialTitle, Genre genre, Year year) {
		String query = SELECT_ALL;
		if (partialTitle != null || genre != null || year != null) {
			query += " WHERE ";
		}
		if (partialTitle != null) {
			query += String.format("title LIKE '%%%s%%'", partialTitle);
			if (genre != null || year != null) query += " AND ";
		}
		if (genre != null) {
			query += String.format("genre = '%s'", genre.toString());
			if (year != null) query += " AND ";
		}
		if (year != null) {
			query += String.format("year = %s", year.toString());
		}
		return query;
	}

}
