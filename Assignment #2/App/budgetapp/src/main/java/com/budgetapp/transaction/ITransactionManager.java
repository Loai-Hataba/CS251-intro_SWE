package com.budgetapp.transaction;

import java.util.List;
// import java.util.Collections;

public interface ITransactionManager {
    boolean add (String UUID, String source ,double amount, String category, String date, boolean isRecurring ) ;
    boolean remove (int id ) ;
    boolean edit  (int id ) ;
    List <String> summary () ;
    void saveToFile() ;
    // List<? extends ITransaction> fetch();
}