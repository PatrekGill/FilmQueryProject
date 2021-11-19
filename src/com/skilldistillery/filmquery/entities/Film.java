package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class Film {
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;
	
	
	public Film(
			int id, String title, String description, int releaseYear, int languageId, int rentalDuration,
			double rentalRate, int length, double replacementCost, String rating, String specialFeatures
		) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		
		DatabaseAccessorObject dao = new DatabaseAccessorObject();
		this.actors = dao.findActorsByFilmId(id);
	}
	
	/* ------------------------------------------------
	    get/set film ID
	------------------------------------------------ */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	/* ------------------------------------------------
	    get/set Title
	------------------------------------------------ */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/* ------------------------------------------------
	    get/set Description
	------------------------------------------------ */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/* ------------------------------------------------
	    get/set Release Year
	------------------------------------------------ */	
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	
	/* ------------------------------------------------
	    get/set Language ID
	------------------------------------------------ */
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	
	
	/* ------------------------------------------------
	    get/set Title
	------------------------------------------------ */
	public int getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	
	
	/* ------------------------------------------------
	    get/set Length
	------------------------------------------------ */
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	/* ------------------------------------------------
		get/set Rental Rate
	------------------------------------------------ */	
	public double getRentalRate() {
		return replacementCost;
	}
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}
	
	
	
	/* ------------------------------------------------
	    get/set Replacement Cost
	------------------------------------------------ */	
	public double getReplacementCost() {
		return replacementCost;
	}
	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}
	
	
	/* ------------------------------------------------
	    get/set Rating
	------------------------------------------------ */
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
	/* ------------------------------------------------
	    get Special Features List
	------------------------------------------------ */
	public String getSpecialFeatures() {
		return specialFeatures;
	}

	
	
	
	
	/* ------------------------------------------------
	   	Misc
	------------------------------------------------ */
	@Override
	public int hashCode() {
		return Objects.hash(actors, description, id, languageId, length, rating, releaseYear, rentalDuration,
				rentalRate, replacementCost, specialFeatures, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(description, other.description) && id == other.id
				&& languageId == other.languageId && length == other.length && Objects.equals(rating, other.rating)
				&& releaseYear == other.releaseYear && rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		String string = "";
		string += "ID: " + id;
		string += "\nTitle: " + title;
		string += "\nDescription: " + description;
		string += "\nRelease Year: " + releaseYear;
		string += "\nLanguage ID: " + languageId;
		string += "\nRental Duration: " + rentalDuration;
		string += "\nRental Rate: " + rentalRate;
		string += "\nLength: " + length;
		string += "\nReplacement Cost: " + replacementCost;
		string += "\nRating: " + rating;
		string += "\nSpecial Features: " + specialFeatures;
		string += "\nActors: " + actors;
		
		return string;
	}
	
}
