import java.time.LocalDate;

public class Account {
	private int accoutNumber, userId;
	private float accountBalance;
	private boolean isOpen;
	private LocalDate creationDay;
	
	public Account() {
		
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
	
}
