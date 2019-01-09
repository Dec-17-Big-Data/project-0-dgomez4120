package accounts;

import java.io.Serializable;
import java.time.LocalDate;

public class Account implements Comparable<Account>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2079196042011237780L;
	private int accoutNumber, userId;
	private float accountBalance;
	private boolean isOpen;
	private LocalDate creationDay;
	
	public Account() {	
	}
	
	public Account(int acctNum, int userId, float acctBal, boolean isOpen, LocalDate creationDay) {
		this.accoutNumber = acctNum;
		this.userId = userId;
		this.accountBalance = acctBal;
		this.isOpen = isOpen;
		this.creationDay = creationDay;
	}

	public int getAccoutNumber() {
		return accoutNumber;
	}

	public void setAccoutNumber(int accoutNumber) {
		this.accoutNumber = accoutNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public LocalDate getCreationDay() {
		return creationDay;
	}

	public void setCreationDay(LocalDate creationDay) {
		this.creationDay = creationDay;
	}

	public boolean equals(Account other) {
		if(this.getAccoutNumber() == other.getAccoutNumber() &&
				this.getUserId() == other.getUserId() &&
				this.getAccountBalance() == other.getAccountBalance() &&
				this.getCreationDay().equals(other.getCreationDay()) &&
				(this.isOpen() == other.isOpen()) ) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Account o) {
		if(this.getAccoutNumber() < o.getAccountBalance()) {
			return -1;
		}else if(this.getAccoutNumber() > o.getAccoutNumber()) {
			return 1;
		}
		return 0;
	}
	
}
