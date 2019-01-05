
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Console {
	private static Connection con = ConnectionUtil.getConnection();
	
	private static User register() throws SQLException{
		User newRegister = new User();
		String password = "";
		Scanner input = new Scanner(System.in);
		boolean isLegalPassword = false;
		System.out.println("Enter a valid password:\n"+
				"*A valid password can contain letters, numbers, and the following symbols: [!,@,#,$,*,&]*");
		while(!isLegalPassword) {
			String inputPassword = input.next();
			if(!inputPassword.matches("[a-zA-Z0-9|!|@|#|$|*|&]*$")) {
				System.out.println("This password is not valid.\nPlease enter a valid password."
						+"\n*A valid password can contain letters, numbers, and the following symbols: [!,@,#,$,*,&]*");
			}else {
				isLegalPassword = true;
				password = inputPassword;
			}
		}
		
		String sql = "call create_user(?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,newRegister.getFirstName());
		ps.setString(2, newRegister.getLastName());
		ps.setString(3, newRegister.getUsername());
		ps.setString(4, password);
		
		try{
			ps.executeQuery();
			sql = "select user_id from users where username = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, newRegister.getUsername());
			ResultSet rs = ps.executeQuery();
			newRegister.setUserId(rs.getInt("user_id"));
		}catch(SQLException e) {}
		
		System.out.println("You are now registered!");
		
		return newRegister;
	}
	
	private static User login() throws SQLException{
		Scanner input = new Scanner(System.in);
		User loginAcct = null;
		String username, password;
		boolean logged_in = false;
		
		while(!logged_in) {
			
			System.out.println("Username: ");
			username = input.nextLine();

			System.out.println("Password: ");
			password = input.nextLine();
			
			String sql = "select * from users where username = ? and user_password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username.toLowerCase().replaceAll("\\s",""));
			ps.setString(2, password.replaceAll("\\s",""));
			
			try{
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					int userId = rs.getInt("user_id");
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					boolean isSuper, isOpen;
					if(rs.getInt("is_super") == 1) {isSuper = true;}else {isSuper = false;}
					if(rs.getInt("is_open") == 1) {isOpen = true;}else {isOpen = false;}
					loginAcct = new User(userId, firstName, lastName,username, isSuper, isOpen);
					logged_in = username.equals(rs.getString("username")) && password.equals(rs.getString("user_password"));
					
				}
			}catch(SQLException e) {
				System.out.println(e);
			};

			if(logged_in) {
				System.out.println("You're logged in!");
			}else {
				System.out.println("Wrong combination! Try again.");
			}
		}
		return loginAcct;
	}
	
	public static void main(String[] args) {
		System.out.println("\tWelcome to JDBC BANK, how can we help you today?");
		System.out.println("If you would like to set up an account with us, type 'REGISTER'.");
		System.out.println("If you are a returning client, type 'LOGIN'.");
		Scanner input = new Scanner(System.in);
		boolean isCorrectInput = false;
		while(!isCorrectInput) {
			String option = input.nextLine();
			if(option.toLowerCase().equals("login")) {
				isCorrectInput = true;
				try {
					User user = login();
					Menus menu = new Menus();
					menu.toUserMenu(user);
				}catch(SQLException e) {System.out.println("Something happened");}
			}else if(option.toLowerCase().equals("register")) {
				isCorrectInput = true;
				try {
					register();
				}catch(SQLException e) {System.out.println("Something Happened");}
			}else {
				System.out.println("The input was not one of the specified options, please try again.");
			}
		}
		input.close();
	}

}
