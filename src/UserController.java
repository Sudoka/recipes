import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class UserController {
	
	private int numUsers;
	private LinkedList<User> users = new LinkedList<User>();
	
	public UserController() {
		numUsers =0;
		
	}
	
	public User addUser(String username, String password) {
		User newUser = new User(username, password);
		users.add(newUser);
		numUsers++;
		return newUser;
	}
	
	public User addUser() throws IOException {
		
		System.out.println("Username: ");
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		System.out.println("Password: ");
		String password = scan.nextLine();
		User newUser = new User(username, password);
		users.add(newUser);
		numUsers++;
		return newUser;
	}
	
	public User getUser(User u) {
		int loc = users.indexOf(u);
		return users.get(loc);
	}

}
