package application;

import java.util.Scanner;
import users.User;
import users.UserDaoImpl;


public class Console {
	
	public static void login(){
		Scanner input = new Scanner(System.in);
		UserDaoImpl userdi = new UserDaoImpl();
		
		User loginAcct = null;
		String username = "", password = "";
		boolean logged_in = false;
		
		while(!logged_in) {

			System.out.println("Username: ");
			username = input.nextLine();
			username = username.toLowerCase().replaceAll("\\s","");
			
			System.out.println("Password: ");
			password = input.nextLine();
			password = password.replaceAll("\\s","");
			
			logged_in = userdi.isUser(username, password);
			if(!logged_in) {System.out.println("WRONG COMBINATION");}
			else {System.out.println("LOGGING IN");}
		}
		
		loginAcct = userdi.getUser(username, password);
		Menus.toUserMenu(loginAcct);
	}
	
	public static void main(String[] args) {

		System.out.println("\tWelcome to JDBC BANK, how can we help you today?");
		System.out.println("If you would like to set up an account with us, type 'REGISTER'.");
		System.out.println("If you are a returning client, type 'LOGIN'.");
		Scanner input = new Scanner(System.in);
		boolean isCorrectInput = false;
		while(!isCorrectInput) {
			String option = input.nextLine();
			if(option.toLowerCase().trim().equals("login")) {
				isCorrectInput = true;
				login();
			}else if(option.toLowerCase().trim().equals("register")) {
				isCorrectInput = true;
				User user = (new UserDaoImpl()).createUser();
				Menus.toUserMenu(user);
			}else {
				System.out.println("The input was not one of the specified options, please try again.");
			}
		}
		input.close();
	}

	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	} 
}
