import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class RecipeController {
	
	int numRecipe;
	LinkedList<Recipe> recipes = new LinkedList<Recipe>();
	
	public RecipeController() {
		numRecipe =0;
		
	}
	
	public void addRecipe(Recipe rec) {
		recipes.add(rec);
		numRecipe++;
	}
	
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
	
	public Recipe getRecipe(Recipe r) {
		int loc = recipes.indexOf(r);
		return recipes.get(loc);
	}
	
}
