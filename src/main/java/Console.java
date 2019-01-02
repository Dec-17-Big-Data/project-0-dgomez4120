
import java.util.Scanner;


public class Console {

	private static void register() {
		new User();
	}
	
	private static void login() {
		Scanner input = new Scanner(System.in);
		String username, password;
		boolean logged_in = false;
		while(!logged_in) {
			String user_me = "dgomez4120";
			String pass_me = "passw0rd";		
			System.out.println("Login here!");
			System.out.println("Username: ");
			username = input.next();
			
			System.out.println("Password: ");
			password = input.next();
			
			logged_in = username.equals(user_me) && password.equals(pass_me);
			if(logged_in) {
				System.out.println("You're logged in!");
				input.close();
			}else {
				System.out.println("Wrong combination! Try again.");
			}
		}
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
				login();
			}else if(option.toLowerCase().equals("register")) {
				isCorrectInput = true;
				register();
			}else {
				System.out.println("The input was not one of the specified options, please try again.");
			}
		}
		input.close();
	}

}
