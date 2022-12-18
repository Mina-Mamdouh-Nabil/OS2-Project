
package accounts_transaction;

public class AccountsTransaction {

    
    public static Transaction[] createTransactions(int n) {
        Account[] accounts = new Account[n];

        for (int i = 0; i < n; i++) {
            accounts[i] = new Account(i);
        }

        Transaction[] transactions = new Transaction[n];

        for (int i = 0; i < n; i++) {
            Account left = accounts[i];
            Account right = accounts[(i + 1) % n];

            transactions[i] = new Transaction(i, left, right);

        }

        return transactions;
    }
    
    public static void main(String[] args) {
        
        
        Transaction[] transactions = createTransactions(5);
        int i = 0;
        for (Transaction transaction : transactions) {
            transaction.start();
        }
        
    }
    
}
