//import javax.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class Start extends JFrame {

	boolean loggedin;

	public Start() throws IOException {
		loggedin = false;
	}

	public static void draw() {

	}

	/* For future use
	 * public void addRecipe() throws IOException {
	 * 
	 * System.out.println(
	 * "List your ingredients, seperated by an enter. Press enter when done.");
	 * Scanner scan = new Scanner(System.in); String name = scan.nextLine();
	 * String input = scan.nextLine(); ArrayList<String> ingredients = new
	 * ArrayList<String>(); do { ingredients.add(input); //
	 * System.out.println(input); input = scan.nextLine(); } while
	 * (!input.equals("")); Recipe newRec = new Recipe(name, ingredients);
	 * recipes.add(newRec); numRecipe++; }
	 */

	public static void main(String args[]) throws IOException {
		Start mainDisplay = new Start();
		
		// RecipeController Object (holds all recipes)
		/* this is advantageous because we do not want to be working with each recipe
		 * inside of the main class. By passing things to RecipeController, it can 
		 * handle the finding, creating, etc of Recipes. It would be a huge mess
		 * to create Recipes and store them all inside of the main function
		 */
		
		// Create a Recipe Controller
		RecipeController RC = new RecipeController();

		// Tests/Usage examples
		// Recipe Class
		ArrayList<String> testIngredients = new ArrayList<String>(); //  Makes an array list of strings to hold ingredients
		testIngredients.add("Ingredient1"); // adds some ingredients 
		testIngredients.add("Ingredient2");
		Recipe testRec = new Recipe("TestRec1", testIngredients); // creates a new recipe with the ingredients
		RC.addRecipe(testRec); // Adds the created recipe to the Recipe Controller

		// tests to see if everything was initialized correctly, and if ingredients were added correctly. 
		System.out.println("Testing adding ingredients and retreiving them by variable...");
		if (RC.getRecipe(testRec).ingredients.toArray()[0]
				.equals("Ingredient1")) { // tests if the first ingredient in the Recipe "testRec" is equal to the string "Ingredient1"
			System.out.println("Equaled!\n"); // Success
		} else {
			System.out.println("Error: Test Failed\n"); // Failed
		}

		// tests getingredients method and to make sure toArray() works
		System.out
				.println("Testing method getIngredients and sending it toArray() to see if ingredients are equal...");
		if (RC.getRecipe(testRec).getIngredients().toArray()[0]                               
				.equals("Ingredient1")) { // we previously tested the ingredients variable directly. This tests a method we created which turn    
										  // the ingredients list into an array
			System.out.println("Equaled!\n"); // our toArray method worked and correctly returned the ingredients in an array
		} else {
			System.out.println("Error: Test Failed\n"); // method failed
		}

		// tests if recipe has been saved to a text file properly
		System.out.println("Checking to see if information written to file has been stored and can be retrieved correctly...");
		FileReader input = new FileReader("TestRec1.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String line = bufRead.readLine();
		if (line.equals("TestRec1")) {
			System.out.println("Equaled!\n");
		} else {
			System.out
					.println("Error: Problem writing or reading from textfile.\n");
		}

		
		// UserController is an object that can create objects of class User. 
		// very similar to recipecontroller
		UserController testUserController = new UserController();
		// User testUser = new User("testUser", "testPass");
		User testUser = testUserController.addUser("testUser", "testPass");
		System.out.println("Testing added a new User");
		if (testUserController.getUser(testUser).username.equals("testUser")) {
			System.out.println("Equaled! Added user's username is correct.\n");
		} else {
			System.out
					.println("Error: Added username \"testUser\" to Usercontroller, but could not retreive a user by that username\n");
		}

		// testing password comparison
		System.out
				.println("Testing if checkPass method correctly checks user's password");
		if (testUserController.getUser(testUser).checkPass("testPass")) {
			System.out.println("Equaled! Passwords matched up - user can log in\n");
		} else {
			System.out
					.println("Error: Password should be correct but returned false\n");
		}
	}
} 