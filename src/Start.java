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
		RecipeController RC = new RecipeController();

		// TESTS

		// Recipe Class
		ArrayList<String> testIngredients = new ArrayList<String>();
		testIngredients.add("Ingredient1");
		testIngredients.add("Ingredient2");
		Recipe testRec = new Recipe("TestRec1", testIngredients);
		RC.addRecipe(testRec);

		// tests initialization, adding ingredients
		System.out
				.println("Testing adding ingredients and retreiving them by variable...");
		if (RC.getRecipe(testRec).ingredients.toArray()[0]
				.equals("Ingredient1")) {
			System.out.println("Equaled!\n");
		} else {
			System.out.println("Error: Test Failed\n");
		}

		// tests getingredients method and to make sure toArray() works
		System.out
				.println("Testing method getIngredients and sending it toArray() to see if ingredients are equal...");
		if (RC.getRecipe(testRec).getIngredients().toArray()[0]
				.equals("Ingredient1")) {
			System.out.println("Equaled!\n");
		} else {
			System.out.println("Error: Test Failed\n");
		}

		// tests if recipe has been saved to a text file properly
		System.out
				.println("Checking to see if information written to file has been stored and can be retrieved correctly...");
		FileReader input = new FileReader("TestRec1.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String line = bufRead.readLine();
		if (line.equals("TestRec1")) {
			System.out.println("Equaled!\n");
		} else {
			System.out
					.println("Error: Problem writing or reading from textfile.\n");
		}

		
		// User Class
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