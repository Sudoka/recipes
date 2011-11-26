import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections; 



public class UserController{


	private LinkedList<Users> users = new LinkedList<Users>();
	int numUsers;
	
	public UserController() {
		numUsers = users.size();
		try {


			BufferedReader br = new BufferedReader(new FileReader("Users.txt"));
			BufferedReader br2 = new BufferedReader(new FileReader("Passwords.txt"));

			String acc;
			String pass;
			//Read from the original file and write to the new
			//unless content matches data to be removed.
			while ((acc = br.readLine()) != null) {
				pass = br2.readLine();
				users.add(new Users(acc,pass));
				
			}

			br.close();
			br2.close();

		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		

	}
	


	public Users addUser(String username, String password) {

		if(users.indexOf(username) == -1)
		{

			Users newUser = new Users(username, password);
			users.add(newUser);
			numUsers++;
			try {
				PrintWriter pw = new PrintWriter(new FileWriter("Users.txt", true));
				pw.println(username);
				pw.flush();
				pw.close();
				PrintWriter pw2 = new PrintWriter(new FileWriter("Passwords.txt", true));
				pw2.println(password);
				pw2.flush();
				pw2.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return newUser;
		}
		else
			return null;
	}

	public void deleteUser(String username)
	{
		int checker = users.indexOf(username);

		Users temp = users.get(checker);
		String removepass = temp.password;
		users.remove(username);
		numUsers--;

		try {

			File inFile = new File("Users.txt");
			File inFile2 = new File("Passwords.txt");


			//Construct the new file that will later be renamed to the original filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			File tempFile2 = new File(inFile2.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader("Users.txt"));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			BufferedReader br2 = new BufferedReader(new FileReader("Passwords.txt"));
			PrintWriter pw2 = new PrintWriter(new FileWriter(tempFile2));

			String line = null;

			//Read from the original file and write to the new
			//unless content matches data to be removed.
			while ((line = br.readLine()) != null) {

				if (!line.trim().equals(username)) {

					pw.println(line);
					pw.flush();
					pw2.println(line);
					pw2.flush();
				}
			}
			pw.close();
			br.close();
			pw2.close();
			br2.close();

		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}



	}

	public Users addUser() throws IOException {

		System.out.println("Username: ");
		Scanner scan = new Scanner(System.in);
		String username = scan.nextLine();
		System.out.println("Password: ");
		String password = scan.nextLine();
		Users newUser = new Users(username, password);
		users.add(newUser);
		numUsers++;
		return newUser;
	}

	public Users getUser(Users u) {
		int loc = users.indexOf(u);
		return users.get(loc);
	}

	public LinkedList<String> sortUsers()
	{
		LinkedList<String> sorted = new LinkedList<String>();
		Scanner sorter;
		try {
			sorter = new Scanner(new BufferedReader(new FileReader("Users.txt")));
			for(int i = 0; i < numUsers; i++)
			{
				String theUser = sorter.nextLine();
				sorted.add(theUser);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(sorted);
		
		return sorted;
	}

}