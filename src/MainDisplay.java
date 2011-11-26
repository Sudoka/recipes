import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class MainDisplay extends JFrame{
	// Variables declaration for login page/user dashboard
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField jTextField1;
	private JPasswordField jPasswordField1;
	private JButton jButton1;
	private JPanel contentPane;
	public Users person;

	// Variables for user home page
	// End of variables declaration

	public MainDisplay() {
		super();
		create();
		this.setVisible(true);
	}

	private void create() {
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jTextField1 = new JTextField();
		jPasswordField1 = new JPasswordField();
		jButton1 = new JButton();
		contentPane = (JPanel) this.getContentPane();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//
		// jLabel1
		//
		jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		jLabel1.setForeground(new Color(0, 0, 255));
		jLabel1.setText("username:");
		//
		// jLabel2
		//
		jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
		jLabel2.setForeground(new Color(0, 0, 255));
		jLabel2.setText("password:");
		//
		// jTextField1
		//
		jTextField1.setForeground(new Color(0, 0, 255));
		jTextField1.setSelectedTextColor(new Color(0, 0, 255));
		jTextField1.setToolTipText("Enter your username");
		jTextField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextField1_actionPerformed(e);
			}

		});
		//
		// jPasswordField1
		//
		jPasswordField1.setForeground(new Color(0, 0, 255));
		jPasswordField1.setToolTipText("Enter your password");
		jPasswordField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jPasswordField1_actionPerformed(e);
			}

		});
		//
		// jButton1
		//
		jButton1.setBackground(new Color(204, 204, 204));
		jButton1.setForeground(new Color(0, 0, 255));
		jButton1.setText("Login");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton1_actionPerformed(e);
			}

		});
		//
		// contentPane
		//
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createEtchedBorder());
		contentPane.setBackground(new Color(204, 204, 204));
		addComponent(contentPane, jLabel1, 5, 10, 106, 18);
		addComponent(contentPane, jLabel2, 5, 47, 97, 18);
		addComponent(contentPane, jTextField1, 110, 10, 183, 22);
		addComponent(contentPane, jPasswordField1, 110, 45, 183, 22);
		addComponent(contentPane, jButton1, 150, 75, 83, 28);
		//
		// login
		//
		this.setTitle("Login To Members Area");
		this.setLocation(new Point(76, 182));
		this.setSize(new Dimension(335, 141));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		container.add(c);
	}

	private void jTextField1_actionPerformed(ActionEvent e) {

	}

	private void jPasswordField1_actionPerformed(ActionEvent e) {

	}

	@SuppressWarnings("deprecation")
	private void jButton1_actionPerformed(ActionEvent e) {
		System.out.println("\njButton1_actionPerformed(ActionEvent e) called.");
		String username = new String(jTextField1.getText());
		String password = new String(jPasswordField1.getText());
		Vector<String> holderac = new Vector<String>();
		Vector<String> holderpass = new Vector<String>();
		int checker = 0;


		Scanner s;
		try {
			s = new Scanner(new BufferedReader(new FileReader("Users.txt")));
			while(s.hasNext())
			{
				String acc = s.nextLine();
				holderac.add(acc);
			}
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Scanner s2;
		try {
			s2 = new Scanner(new BufferedReader(new FileReader("Passwords.txt")));
			while(s2.hasNext())
			{
				String acc = s2.nextLine();
				holderpass.add(acc);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		for(int i = 0; i < holderac.size(); i++)
		{
			if(username.equals(holderac.elementAt(i)))
			{
				checker = i;
			}
		}




		if (username.equals("") || password.equals("")) // If password and
			// username is empty >
			// Do this >>>
		{
			jButton1.setEnabled(false);
			JLabel errorFields = new JLabel(
			"<HTML><FONT COLOR = Blue>You must enter a username and password to login.</FONT></HTML>");
			JOptionPane.showMessageDialog(null, errorFields);
			jTextField1.setText("");
			jPasswordField1.setText("");
			jButton1.setEnabled(true);
			this.setVisible(true);
		} 

		//********************************User successfully logged in****************//
		else {
			JLabel optionLabel = new JLabel(
					"<HTML><FONT COLOR = Blue>You entered</FONT><FONT COLOR = RED> <B>"
					+ username
					+ "</B></FONT> <FONT COLOR = Blue>as your username.<BR> Is this correct?</FONT></HTML>");
			int confirm = JOptionPane.showConfirmDialog(null, optionLabel);
			switch (confirm) { // Switch > Case
			case JOptionPane.YES_OPTION: // Attempt to Login user
				String use = jTextField1.getText();
				String pass = jPasswordField1.getText();
				System.out.printf("user: %s\npass: %s", use, pass);



				if (use.equals(holderac.elementAt(checker)) && pass.equals(holderpass.elementAt(checker))) {

					JLabel welcome = new JLabel("<HTML><FONT COLOR = BLUE> Welcome " + use + "!</FONT></HTML>");
					JOptionPane.showMessageDialog(null, welcome);
					home();

					/**** reorganizes original login screen to a user dashboard **/
					contentPane.remove(jButton1);
					contentPane.remove(jPasswordField1);
					contentPane.remove(jTextField1);
					contentPane.remove(jLabel2);
					jLabel1.setText("username: " + use);
					this.person = new Users(use, pass);
					if(person.getAdmin())
					{
						for (int i = 0; i < holderac.size(); i++)
						{
							person.addUser(holderac.elementAt(i), holderpass.elementAt(i));
						}
					}


					this.setVisible(false);


					this.repaint();
					this.validate();
				} 

				else {
					JLabel badUsePass = new JLabel("<HTML><FONT COLOR = BLUE> Bad User name or Password</FONT></HTML>");
					JOptionPane.showMessageDialog(null, badUsePass);
					jTextField1.setText("");
					jPasswordField1.setText("");
					jButton1.setEnabled(true); // Set button enable to false to
					// prevent 2 login attempts
				}
				break;

			case JOptionPane.NO_OPTION: // No Case.(Go back. Set text to 0)
				jButton1.setEnabled(false);
				jTextField1.setText("");
				jPasswordField1.setText("");
				jButton1.setEnabled(true);
				break;

			case JOptionPane.CANCEL_OPTION: // Cancel Case.(Go back. Set text to
				// 0)
				jButton1.setEnabled(false);
				jTextField1.setText("");
				jPasswordField1.setText("");
				jButton1.setEnabled(true);
				break;

			} // End Switch > Case

		}
	}

	public void home()  {

		JFrame frame = new JFrame("iRecipe");

		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		JMenu file = new JMenu("File");
		menubar.add(file);
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);

		JMenu options = new JMenu("Options");
		menubar.add(options);
		JMenuItem addUser = new JMenuItem("Add User");
		options.add(addUser);
		//addUser.addActionListener(new AddingUser());
		JMenuItem deleteUser = new JMenuItem("Delete User");
		options.add(deleteUser);

		exit.addActionListener(new Exiting());



		JPanel panel = new JPanel(new GridBagLayout());
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagConstraints c = new GridBagConstraints();
		JLabel title = new JLabel("iRecipe");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		panel.add(title, c);
		JButton search = new JButton("Search");
		search.addActionListener(new Action());
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		panel.add(search, c);
		JTextField searchText = new JTextField("Search for a recipe here", 20);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		panel.add(searchText, c);
		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 2;
		JLabel recipe = new JLabel("Random Recipe");
		panel.add(recipe, c);
		JPanel panel2 = new JPanel(new GridBagLayout());
		JButton add = new JButton("Add Recipe");
		c.gridx = 5;
		c.gridy = 100;
		c.gridwidth = 10;
		panel2.add(add,c);
		frame.add(panel2, BorderLayout.WEST);
		add.addActionListener(new Adding());







		// panel.add(title, c);

		frame.setVisible(true);
	}

	/*class AddingUser implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JFrame addingUserFrame = new JFrame("Adding User");
			
		}
	}*/

	class Exiting implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}

	}
	class Adding implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JFrame addingFrame = new JFrame("Adding Recipe");
			addingFrame.setSize(350,350);
			JPanel loginPanel = new JPanel(new GridBagLayout());
			addingFrame.getContentPane().add(loginPanel, BorderLayout.NORTH);
			GridBagConstraints c = new GridBagConstraints();
			JLabel loginHeader = new JLabel("<Will display search results when project is finished>");
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 2;
			loginPanel.add(loginHeader, c);

			addingFrame.setVisible(true);


		}


	}

	class Action implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFrame loginFrame = new JFrame("Search");
			loginFrame.setSize(350, 350);
			JPanel loginPanel = new JPanel(new GridBagLayout());
			loginFrame.getContentPane().add(loginPanel, BorderLayout.NORTH);
			GridBagConstraints c = new GridBagConstraints();
			JLabel loginHeader = new JLabel("<Will display search results when project is finished>");
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 2;
			loginPanel.add(loginHeader, c);


			c.gridx = 1;
			c.gridy = 1;


			c.gridy = 2;
			c.gridx = 0;



			c.gridy = 2;
			c.gridx = 1;

			loginFrame.setVisible(true);
		}
	}
	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager
			.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		new MainDisplay();
	};

}
