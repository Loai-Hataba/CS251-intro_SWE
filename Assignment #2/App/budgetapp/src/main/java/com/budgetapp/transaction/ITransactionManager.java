package com.budgetapp.transaction;

import java.util.List;
import java.util.Date;
// import java.util.Collections;

public interface ITransactionManager {
    boolean add (String  title , Date date , String category , double amount , boolean isRecurring ) ;
    boolean remove (int id ) ;
    boolean edit  (int id ) ;
    List <String> summary () ;
    void saveToFile() ;
    // List<? extends ITransaction> fetch();
}
