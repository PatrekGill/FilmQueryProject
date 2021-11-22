package com.skilldistillery.filmquery.menu;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmDatabaseMenu extends Menu {

	public FilmDatabaseMenu(Scanner scanner) {
		super(scanner);
	}
	
	
	/* ------------------------------------------------
	    openMainMenu
	------------------------------------------------ */
	public void openMainMenu() {
		int choice;
		boolean quit = false;
		
        do {
            System.out.println("\n===============================");
            System.out.println("0. Exit");
            System.out.println("1. Look up film by id");
            System.out.println("2. Search for films by keywords");
            System.out.println("===============================");

            choice = getNextInt("Your choice: ");
            switch (choice) {
	            case 0: {
	            	System.out.println("Quitting...");
	            	quit = true;
	                break;
	            }
                case 1: {
                	lookUpFilmById();
                    break;
                }
                case 2: {
                	lookUpFilmsByKeywords();
                    break;
                }
                default: {
                	printInvalidEntry();
                    break;
                }
            }

            System.out.println(); // gap in console

        } while (!quit);
	}
	
	
	/* ------------------------------------------------
	    lookUpFilmById
	------------------------------------------------ */	
	private void lookUpFilmById() {
		int idToSearchFor;
		boolean quit = false;
		
		do {
			System.out.println("\n===============================");
			System.out.println("Film ID Search");
			System.out.println("Enter -1 To Return To Main Menu");
            System.out.println("===============================");
            idToSearchFor = getNextInt("Film ID: ");
            
            
            if (idToSearchFor < 0 && idToSearchFor != -1) {
            	printInvalidEntry();
            	
            } else if (idToSearchFor == -1) {
            	System.out.println("Returning to main menu...");
            	quit = true;
            	
            } else {
            	DatabaseAccessorObject dao = new DatabaseAccessorObject();
            	Film film = dao.findFilmById(idToSearchFor);
            	
            	if (film == null) {
            		System.out.println("Could not find a film with the id: " + idToSearchFor);
            	} else {
            		System.out.println(film.toStringSimple());
            	}
            	
            }
            
            System.out.println(); // gap in console
            
		} while (!quit);
	}
	
	
	/* ------------------------------------------------
	    lookUpFilmsByKeywords
	------------------------------------------------ */
	private void lookUpFilmsByKeywords() {
		String searchTerm;
		boolean quit = false;
		
		do {
			System.out.println("\n=======================================");
			System.out.println("Film Keyword Search");
			System.out.println("Enter In the Keyword to search film titles and descriptions");
			System.out.println("Type '\\exit' to return to the Main Menu");
	        System.out.println("=======================================");
	        searchTerm = getNextLine("Search: ");
	        
	        
	        if (searchTerm.equals("\\exit")) {
	        	System.out.println("Returning to main menu...");
	        	quit = true;
	        	
	        } else {
	        	DatabaseAccessorObject dao = new DatabaseAccessorObject();
	        	List<Film> films = dao.findFilmsByKeyword(searchTerm);
	        	
	        	if (films.size() == 0) {
	        		System.out.println("Could not find any films with the term: " + searchTerm);
	        		
	        	} else {
	        		for (Film film : films) {
						System.out.println(film.toStringSimple());
					}
	        		
	        		System.out.println("\nReturned " + films.size() + " entries for: " + searchTerm);
	        		
	        	}
	        	
	        }
	        
	        System.out.println(); // gap in console
	        
		} while (!quit);
	}
}


























