package User;

import java.util.Date;

public class User {

    int userId ;
    String userName ;
    String password  ;
    String Country ;
    String phoneNumber ;

    public User(int userId, String userName, String password, String Country, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.Country = Country;
        this.phoneNumber = phoneNumber;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;

    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCountry() {
        return Country;
    }
    public void setCountry(String country) {
        Country = country;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean signup(String userName , String email ,String password)
    {
        // for now return true cuz we are right
        return true;
    }
    public boolean login (String userName , String password) {
        // for now return ture cuz we are right
        return true;
    }

    public boolean logout() {
        // for now return ture cuz we are right
        return true;
    }
    public void incomeView () {
        return ;
    }
    public boolean addIncome (String incomeSrc , float amount) {
        // for now return true cuz we are right
        return true;
    }
    public void budgetView () {
        return ;
    }
    public boolean addBudget (String budgetSrc , float amount) {
        // for now return true cuz we are right
        return true;
    }
    public boolean addReminder (String reminderTitle , Date date , float amount) {
        // for now return true cuz we are right
        return true;
    }
    public void expenseView () {
        return ;
    }
    public boolean addExpense (String category , float amount , Date date ) {
        return true ;
    }

}
