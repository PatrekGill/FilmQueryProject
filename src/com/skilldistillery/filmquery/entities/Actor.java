package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Actor {
	private int id;
	private String firstName;
	private String lastName;
	private List<Film> films;
	
	public Actor() {
	}
	public Actor(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	
	/* ------------------------------------------------
	    get/set Actor ID
	------------------------------------------------ */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	/* ------------------------------------------------
	    get/set First Name
	------------------------------------------------ */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	/* ------------------------------------------------
	    get/set Last Name
	------------------------------------------------ */
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	/* ------------------------------------------------
	    films method
	------------------------------------------------ */
	public boolean addFilm(Film film) {
		return films.add(film);
	}
	public Film removeFilm(int index) {
		return films.remove(index);
	}
	public boolean removeFilm(Film film) {
		return films.remove(film);
	}
	public void addAllFilms(List<Film> films) {
		this.films.addAll(films);
	}

	
	/* ------------------------------------------------
	   	Misc
	------------------------------------------------ */
	// toString
	// hashcode
	// equals
	
	
	
}
