import java.util.*;
import java.io.*;

public class Recipe {

	public String name;
	double rating;
	int numRates;
	ArrayList<String> ingredients = new ArrayList<String>();
	
	public Recipe(String name, ArrayList<String> input) throws IOException {
		this.name = name;
		this.ingredients = input;
		// creates text file with data
		File f = new File(name+".txt");
		PrintStream out = new PrintStream(new FileOutputStream(name+".txt"));
			f.createNewFile();
		out.println(name);
		for(int i=0; i<ingredients.size(); i++) {
			 out.println(ingredients.toArray()[i]);
		}	
	}
	
	// checks if this recipes' ingredients or name contains the string passed to it.
	public boolean contains(String s) {
		if(name.equals(s)) {
			return true;
		}
		
		for(String ingredientStr : ingredients) {
			if(s.equalsIgnoreCase(ingredientStr)) {
				return true;
			}
		}
		
		return false;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public double rate(int rating) {
		double tempRate = this.rating*numRates;
		numRates++;
		tempRate += rating;
		this.rating = tempRate/numRates;
		return this.rating;
	}
	
	
	
}
