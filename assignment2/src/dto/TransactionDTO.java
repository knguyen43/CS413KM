public class TransactionDTO {
    private String transactionId;
    private String accountNumber;
    private double amount;
    private Date transactionDate;
    private String type; // Such as DEPOSIT, WITHDRAWAL, etc.
    private String status; // Such as PENDING, COMPLETED, etc.

    // Constructor
    public TransactionDTO(String transactionId, String accountNumber, double amount, Date transactionDate, String type, String status) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.type = type;
        this.status = status;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
