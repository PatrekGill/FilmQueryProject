package com.skilldistillery.filmquery.menu;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmDatabaseMenu extends Menu {

	public FilmDatabaseMenu(Scanner scanner) {
		super(scanner);
	}
	
	public void openMainMenu() {
		int choice;
		boolean quit = false;
		
        do {
            System.out.println("\n===========================");
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
            		System.out.println(film);
            	}
            	
            }
            
            System.out.println(); // gap in console
            
		} while (!quit);
	}
}


























