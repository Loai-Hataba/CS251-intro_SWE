package com.budgetapp.user;
//?
public class BankAccount {
    private  String bankName;
    private  String accountNumber;
    private String accountHolderName ;
    private float balance;

    // The Constructor :
    public BankAccount(String bankName, String accountNumber, String accountHolderName) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        // setting the balance to a default amount
        this.balance = 1000f;
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


    //! the class methods :
    // This function returns ArrayList of string
    public void fetchBankData () {
        // simulating fetching the data from the bank account
        System.out.println("Fetching Bank Data... for " + accountNumber + "that belongs to ..." + bankName);
    }
    public void fetchAccountBalance() {
        // simulating fetching the account balance

        System.out.println("Fetching Account Balance... for " + accountNumber + "that belongs to ..." + accountHolderName);

        System.out.println("The account balance is: " + this.balance);
    }


}
