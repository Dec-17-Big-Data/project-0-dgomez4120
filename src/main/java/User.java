import java.util.Scanner;


public class User {
	private String firstName, lastName, username, password;
	private boolean isAdmin;
	
	private Scanner input = new Scanner(System.in);
	
	protected User() {
		this.setFirstName();
		this.setLastName();
		this.setUsername();
		this.setPassword();
		this.setIsAdmin();
	}
	
	protected void setFirstName() {
		boolean isLegalFirstName = false;
		while(!isLegalFirstName) {
			System.out.println("Enter your first name: ");
			String inputFirstName = input.next();
			if(!inputFirstName.chars().allMatch(Character::isLetter)) {
				System.out.println("Your first name must only contain letters, please try again.");
			}else {
				isLegalFirstName = true;
				this.firstName = inputFirstName;
			}
		}
	}
	
	protected void setLastName() {
		boolean isLegalLastName = false;
		while(!isLegalLastName) {
			System.out.println("Enter your last name: ");
			String inputLastName = input.next();
			if(!inputLastName.chars().allMatch(Character::isLetter)) {
				System.out.println("Your last name must only contain letters, please try again.");
			}else {
				isLegalLastName = true;
				this.lastName = inputLastName;
			}
		}
	}
	
	protected void setUsername() {
		boolean isLegalUsername = false;
		while(!isLegalUsername) {
			System.out.println("Enter a valid username:\n"
					+ "*A valid username must begin with a letter and contain only numbers and letters*");
			String inputUsername = input.next();
			if(!Character.isLetter(inputUsername.charAt(0)) || !inputUsername.chars().allMatch(Character::isLetterOrDigit)) {
				System.out.println("This username is not valid.\nEnter a valid username.");
			}else {
				isLegalUsername = true;
				this.username = inputUsername;
			}
		}
	}
	
	protected void setPassword() {
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
				this.password = inputPassword;
			}
		}
	}
	
	protected void setIsAdmin(){
		this.isAdmin = false;
	}
		
}
