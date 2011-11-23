import java.util.ArrayList;


public class User {

	String username;
	String password;
	boolean admin;
	ArrayList<String> recipes = new ArrayList<String>();
	
	public User (String username, String password) {
		this.username = username;
		this.password  = password;
		//recipesAdded=0;
	}
	
	boolean checkPass(String s) {
		if (s.equals(password)) {
			return true;
		}
		else return false;
	}
	
}
