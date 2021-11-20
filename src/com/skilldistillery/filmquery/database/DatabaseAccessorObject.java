package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String USER = "student";
	private static final String PASSWORD = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
	}

	/* ------------------------------------------------
	    openConnection
	------------------------------------------------ */
	private Connection openConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return connection;
	}
	
	
	/* ------------------------------------------------
	    createFilmFromResultSet
	------------------------------------------------ */
	private Film createFilmFromResultSet(ResultSet resultSet) throws SQLException {
		int languageId = resultSet.getInt("language_id");
		
		return new Film(
			resultSet.getInt("id"),
			resultSet.getString("title"), 
			resultSet.getString("description"), 
			resultSet.getInt("release_year"), 
			languageId, 
			resultSet.getInt("rental_duration"),
			resultSet.getDouble("rental_rate"),
			resultSet.getInt("length"),
			resultSet.getDouble("replacement_cost"),
			resultSet.getString("rating"),
			resultSet.getString("special_features"),
			getLanguageNameById(languageId)
		);
	}
	
	
	
	/* ------------------------------------------------
	    createActorFromResultSet
	------------------------------------------------ */
	private Actor createActorFromResultSet(ResultSet resultSet) throws SQLException {
		return new Actor(
			resultSet.getInt("id"),
			resultSet.getString("first_name"),
			resultSet.getString("last_name")
		);
	}
	
	
	
	/* ------------------------------------------------
	    getLanguageNameById
	------------------------------------------------ */
	public String getLanguageNameById(int languageId) {
		String string = "";
		String sql = "SELECT * FROM language WHERE id = ?";
		
		try {
			Connection connection = openConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, languageId);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				string = resultSet.getString("name");
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return string;	
	}
	
	
	
	/* ------------------------------------------------
	    findFilmById
	------------------------------------------------ */
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT * FROM film WHERE id = ?";
		
		try {
			Connection connection = openConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, filmId);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				film = createFilmFromResultSet(resultSet);
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return film;
	}
	
	
	/* ------------------------------------------------
	    findActorById
	------------------------------------------------ */
	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

		try {
			Connection connection = openConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				actor = createActorFromResultSet(resultSet);
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return actor;
	}
	
	
	/* ------------------------------------------------
	    findFilmsByActorId
	------------------------------------------------ */
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		
		String sql = 
				"SELECT "
			+ 		"id, title, description, release_year, language_id, rental_duration, "
			+ 		"rental_rate, length, replacement_cost, rating, special_features "
			+ 	"FROM film "
			+ 		"JOIN film_actor "
			+ 			"ON film.id = film_actor.film_id "
			+ 	"WHERE actor_id = ?";

		
		try {
			Connection connection = openConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Film film = createFilmFromResultSet(resultSet);
				films.add(film);
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		return films;
	}
	
	
	
	/* ------------------------------------------------
	    findActorsByFilmId
	------------------------------------------------ */
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		
		String sql = 
				"SELECT * "
			+ 	"FROM actor "
			+ 		"JOIN film_actor "
			+ 			"ON film_actor.actor_id = actor.id "
			+ 	"WHERE film_actor.film_id = ?";

		try {
			Connection connection = openConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, filmId);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Actor actor = createActorFromResultSet(resultSet);
				actors.add(actor);
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		return actors;
	}
	
	
	/* ------------------------------------------------
	    findFilmsByKeyword
	----------------------------------------------- */
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		
		String sql = 
				"SELECT * "
			+ 	"FROM film "
			+ 	"WHERE description LIKE ? OR title LIKE ?";
		
		try {
			Connection connection = openConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			keyword = "%" + keyword + "%";
			statement.setString(1, keyword);
			statement.setString(2, keyword);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Film film = createFilmFromResultSet(resultSet);
				films.add(film);
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		return films;
	}

}
