package User;

public class BankAccount {
    public  String bankName;
    public  String accountNumber;
    public String accountHolderName ;
    public float balance;

    // The Constructor :
    public BankAccount(String bankName, String accountNumber, String accountHolderName, float balance) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    // The getter functions
    public String getBankName() {
        return bankName;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public float getBalance() {
        return balance;
    }
    // The setter functions
    public void setBalance(float balance) {
        this.balance = balance;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    // the class methods :
    public void unLinkAccount() {
        return ;
    }
    // This function returns ArrayList of string
    public void fetchBankData () {
        return    ;
    }
    public void fetchAccountBalance() {}


}
