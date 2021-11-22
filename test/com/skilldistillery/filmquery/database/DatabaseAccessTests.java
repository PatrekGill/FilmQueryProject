package com.skilldistillery.filmquery.database;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

class DatabaseAccessTests {
	private DatabaseAccessorObject db;

	@BeforeEach
	void setUp() throws Exception {
		db = new DatabaseAccessorObject();
	}

	@AfterEach
	void tearDown() throws Exception {
		db = null;
	}

	@Test
	void test_findFilmById_with_invalid_id_returns_null() {
		Film f = db.findFilmById(-42);
		assertNull(f);
	}
	
	
	@Test
	void test_findActorById_with_invalid_id_returns_null() {
		Actor actor = db.findActorById(-536);
		assertNull(actor);
	}
	
	
	@Test
	void test_getLanguageNameById_with_id_of_1_returns_english() {
		String language = db.getLanguageNameById(1);
		assertEquals(language, "English");
	}
	@Test
	void test_getLanguageNameById_with_invalid_id_returns_unknown_language_with_id() {
		String language = db.getLanguageNameById(-1);
		assertEquals(language, "Unknown Language (ID: -1)");
	}
	
	
	@Test
	void test_findActorsByFilmId_with_invalid_id_returns_empty_list() {
		List<Actor> actors = db.findActorsByFilmId(-1);
		assertTrue(actors.isEmpty());
	}
	
	
	@Test
	void test_findFilmsByKeyword_with_empty_string_returns_empty_list() {
		List<Film> films = db.findFilmsByKeyword("");
		assertTrue(films.isEmpty());
	}

}
