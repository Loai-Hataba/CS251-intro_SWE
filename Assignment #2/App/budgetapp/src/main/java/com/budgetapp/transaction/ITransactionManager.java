package com.budgetapp.transaction;

import java.util.List;
// import java.util.Collections;

public interface ITransactionManager {
    boolean add (String UUID, String source ,double amount, String category, String date, boolean isRecurring ) ;
    boolean remove (String UUID , int id ) ;
    boolean edit  (String UUID , int id , String source ,double amount, String category, String date, boolean isRecurring ) ;
    List <String> summary (String UUID) ;
    //void saveToFile() ;
    // List<? extends ITransaction> fetch();
}