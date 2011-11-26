import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class RecipeController {

	int numRecipe;
	LinkedList<Recipe> recipes = new LinkedList<Recipe>();

	// constructor. Allows adding or recipes.
	public RecipeController() {
		numRecipe =0;
	}

	// adds a recipe to ArrayList recipes by passing in a pre-made recipe.
	public void addRecipe(Recipe rec) {
		recipes.add(rec);
		numRecipe++;
	}

	// add recipe on-the-fly. Call method to create a recipe from input directly.
	public void addRecipe() throws IOException {

		System.out.println("List your ingredients, seperated by an enter. Press enter when done.");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		String input = scan.nextLine();
		ArrayList<String> ingredients = new ArrayList<String>();
		do {
			ingredients.add(input);
			// System.out.println(input);
			input = scan.nextLine();
		} while (!input.equals(""));
		Recipe newRec = new Recipe(name, ingredients);
		recipes.add(newRec);
		numRecipe++;
	}
	
	// returns an ArrayList of recipes. Pass it a string and it'll add Recipes that match that description to the returned arraylist.
	public ArrayList<Recipe> search(String s) {
		ArrayList<Recipe> results = new ArrayList<Recipe>();
		for(Recipe r : recipes) {
			if(r.contains(s)) {
				results.add(r);
			}
		}
		return results;
	}
	
// returns recipes. Not useful for actual application - just used for testing purposes. Use search method for finding recipes.
	public Recipe getRecipe(Recipe r) {
		int loc = recipes.indexOf(r);
		return recipes.get(loc);
	}

}