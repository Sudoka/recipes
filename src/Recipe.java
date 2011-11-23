import java.util.*;
import java.io.*;

public class Recipe {

	private String name;
	private double rating;
	private int numRates;
	// ingredients is only public for testing. Should be private.
	public ArrayList<String> ingredients = new ArrayList<String>();
	
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
	
	//implement rating
	
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
