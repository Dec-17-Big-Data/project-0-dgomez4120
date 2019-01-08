import java.util.Scanner;


public class User {
	private int userId;
	private String firstName, lastName, username;
	private boolean isAdmin, isOpen;
	
	private Scanner input = new Scanner(System.in);
	
	protected User() {
		this.setFirstName();
		this.setLastName();
		this.setUsername();
		this.setIsAdmin(false);
		this.setIsOpen(true);
	}
	protected User(int userId, String firstName, String lastName, String username, boolean isAdmin, boolean isOpen) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.isAdmin = isAdmin;
		this.isOpen = isOpen;
	}
	
	protected void setFirstName() {
		boolean isLegalFirstName = false;
		while(!isLegalFirstName) {
			System.out.println("First name: ");
			String inputFirstName = input.next();
			if(!inputFirstName.chars().allMatch(Character::isLetter)) {
				System.out.println("The first name must only contain letters, please try again.");
			}else {
				isLegalFirstName = true;
				inputFirstName = inputFirstName.substring(0,1).toUpperCase()+inputFirstName.substring(1);
				this.firstName = inputFirstName.trim();
			}
		}
	}
	
	protected void setLastName() {
		boolean isLegalLastName = false;
		while(!isLegalLastName) {
			System.out.println("Last name: ");
			String inputLastName = input.next();
			if(!inputLastName.chars().allMatch(Character::isLetter)) {
				System.out.println("The last name must only contain letters, please try again.");
			}else {
				isLegalLastName = true;
				inputLastName = inputLastName.substring(0, 1).toUpperCase()+inputLastName.substring(1);
				this.lastName = inputLastName.trim();
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
	
	protected void setIsAdmin(boolean flag){
		this.isAdmin = flag;
	}
	
	protected void setIsOpen(boolean flag) {
		this.isOpen = flag;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	protected String getFirstName() {
		return this.firstName;
	}
	
	protected String getLastName() {
		return this.lastName;
	}
	
	protected String getUsername() {
		return this.username;
	}
	
	protected boolean isAdmin() {
		return this.isAdmin;
	}
	
	protected boolean isOpen() {
		return this.isOpen;
	}
	
	protected int getUserId() {
		return this.userId;
	}
		
}
