import java.util.Date;

public class Account {
    private String accountNumber;
    private String customerId;
    private double balance;
    private Date creationDate;

    public Account() {
    }

    public Account(String accountNumber, String customerId, double balance, Date creationDate) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
