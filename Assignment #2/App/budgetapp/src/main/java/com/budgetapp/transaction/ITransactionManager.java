package com.budgetapp.transaction;


import java.util.List;
import java.util.Date ;
public interface ITransactionManager {


    boolean add (String  title , Date date , String category , double amount , boolean isRecurring ) ;
    boolean remove (int id ) ;
    boolean edit  (int id ) ;
    void display () ;
    List <String> summary () ;
     void saveToFile() ;
}
