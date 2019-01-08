import java.util.List;

public interface TransactionDao {

	public List<Transaction> getUserTransactions(User user);
	public void createTransaction(User user, boolean isWithdraw, List<Account> accounts);
	
}
