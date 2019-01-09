package transactions;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {

	private int transactionId, accountNumber, userID;
	private boolean isWithdraw;
	private LocalDate transactionDate;
	private LocalTime transactionTime; 
	private float transactionAmount;
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public boolean isWithdraw() {
		return isWithdraw;
	}
	public void setWithdraw(boolean isWithdraw) {
		this.isWithdraw = isWithdraw;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public LocalTime getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}
	public float getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	
	
}
