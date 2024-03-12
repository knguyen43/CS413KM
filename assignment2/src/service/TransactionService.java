public class TransactionService {
    private TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public TransactionDTO getTransactionById(long id) {
        return transactionDAO.get(id);
    }

    // Additional service methods for transactions
}
