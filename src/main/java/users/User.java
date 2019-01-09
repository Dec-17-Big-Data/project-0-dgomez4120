package users;

import java.io.Serializable;
import java.util.Scanner;


public class User implements Serializable, Comparable<User>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4368513462082007773L;
	
	private int userId;
	private String firstName, lastName, username;
	private boolean isAdmin, isOpen;
	
	private Scanner input = new Scanner(System.in);
	
	public User() {
		this.setFirstName();
		this.setLastName();
		this.setUsername();
		this.setIsAdmin(false);
		this.setIsOpen(true);
	}
	public User(int userId, String firstName, String lastName, String username, boolean isAdmin, boolean isOpen) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.isAdmin = isAdmin;
		this.isOpen = isOpen;
	}
	
	public void setFirstName() {
		boolean isLegalFirstName = false;
		while(!isLegalFirstName) {
			System.out.println("First name: ");
			String inputFirstName = input.nextLine();
			inputFirstName = inputFirstName.trim();
			if(!inputFirstName.matches("^[ A-Za-z]+$")) {
				System.out.println("The first name must only contain letters, please try again.");
			}else {
				isLegalFirstName = true;
				inputFirstName = inputFirstName.substring(0,1).toUpperCase()+inputFirstName.substring(1).toLowerCase();
				this.firstName = inputFirstName;
			}
		}
	}
	
	public void setLastName() {
		boolean isLegalLastName = false;
		while(!isLegalLastName) {
			System.out.println("Last name: ");
			String inputLastName = input.nextLine();
			inputLastName = inputLastName.trim();
			if(!inputLastName.matches("^[ A-Za-z]+$")) {
				System.out.println("The last name must only contain letters, please try again.");
			}else {
				isLegalLastName = true;
				inputLastName = inputLastName.substring(0, 1).toUpperCase()+inputLastName.substring(1).toLowerCase();
				this.lastName = inputLastName;
			}
		}
	}
	
	public void setUsername() {
		boolean isLegalUsername = false;
		while(!isLegalUsername) {
			System.out.println("Enter a valid username:\n"
					+ "*A valid username must begin with a letter and contain only numbers and letters(SPACES WILL BE REMOVED)*");
			String inputUsername = input.nextLine();
			inputUsername = inputUsername.replaceAll("\\s","");
			if(!Character.isLetter(inputUsername.charAt(0)) || !inputUsername.chars().allMatch(Character::isLetterOrDigit)) {
				System.out.println("This username is not valid.\nEnter a valid username.");
			}else {
				isLegalUsername = true;
				this.username = inputUsername;
			}
		}
	}
	
	public void setIsAdmin(boolean flag){
		this.isAdmin = flag;
	}
	
	public void setIsOpen(boolean flag) {
		this.isOpen = flag;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public boolean isAdmin() {
		return this.isAdmin;
	}
	
	public boolean isOpen() {
		return this.isOpen;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public boolean equals(User user2) {
		if(this.getUserId() == user2.getUserId() 
				&& this.getFirstName().equals(user2.getFirstName())
				&& this.getLastName().equals(user2.getLastName())
				&& this.getUsername().equals(user2.getUsername())
				&& (this.isAdmin() == user2.isAdmin())
				&& (this.isOpen() == user2.isOpen())) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int compareTo(User arg0) {
		if(this.getUserId() < arg0.getUserId()) {
			return -1;
		}else if(this.getUserId() > arg0.getUserId()) {
			return 1;
		}
		return 0;
	}
		
}
