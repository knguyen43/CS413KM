public class AccountDTO {
    private String accountNumber;
    private String customerId;
    private double balance;

    public AccountDTO() {
    }

    public AccountDTO(String accountNumber, String customerId, double balance) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
    }

    // Getters and setters
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
}
