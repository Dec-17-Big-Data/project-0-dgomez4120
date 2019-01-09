package transactions;

import java.util.List;
import accounts.Account;
import users.User;

public interface TransactionDao {

	public List<Transaction> getUserTransactions(User user);
	public void createTransaction(User user, boolean isWithdraw, List<Account> accounts);
	
}
