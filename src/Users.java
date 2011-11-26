import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Users extends UserController{

	String username;
	String password;
	boolean admin = false;
	ArrayList<String> recipes = new ArrayList<String>();

	public Users (String name, String password) {
		this.username = name;
		this.password  = password;
		this.admin = checkPass(username);
		//System.out.println(admin);
		//recipesAdded=0;
	}
	
	public boolean getAdmin()	{
		
		return admin;
	}
	

	boolean checkPass(String s) {
		Scanner checkadmin;
		try {
			
			checkadmin = new Scanner(new BufferedReader(new FileReader("Admin.txt")));
			while(checkadmin.hasNext())
			{
				String acc = checkadmin.nextLine();
				if (acc.equals(username)) {
					return true;
				}
			}
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return false;

	}
	




}
