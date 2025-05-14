# Java Budgeting App 💰

A simple and user-friendly budgeting application built in Java to help users track their income, expenses, and manage monthly budgets effectively.

## Features

- 📊 **Track Income & Expenses**: Add, edit, and delete income or expense records.
- 💼 **Reminders**: Add Reminders for bills, payments, and any debts you may have.
- 📅 **Monthly Budgeting**: Set monthly budget goals and monitor your performance.
- 📈 **Overview Dashboard**: View summaries of your financial status.
- 💾 **Data Persistence**: Save and load data using JSON files.

## Technologies Used

- Java (Standard Edition)
- File I/O for data persistence
- OOP (Object-Oriented Programming) principles
- GSON for handling JSON manipulation

## Getting Started

### Running the App

1. Clone the repository:
   ```bash
   git clone https://github.com/Loai-Hataba/CS251-intro_SWE.git
   cd "Assignment #2"/App/budgetapp

Compile the project:
      javac Main.java

Run the app:
      java Main


### Folder Structure

budgetapp/                                                                                                             
├── src/main/java/com/budgetapp/                                                                                                             
│   ├── budget/                                                                                                             
│   │   ├── Budget.java                                                                                                             
│   │   ├── BudgetManager.java                                                                                                             
│   ├── database/                                                                                                             
│   │   ├── Records.java                                                                                                             
│   │   ├── database.json                                                                                                             
│   ├── methods/                                                                                                             
│   │   ├── Methods.java                                                                                                             
│   │   ├── GsonTool.java                                                                                                             
│   ├── notification/                                                                                                             
│   │   ├── Notification.java                                                                                                             
│   │   ├── NotificationManager.java                                                                                                             
│   ├── reminder/                                                                                                             
│   │   ├── Reminder.java                                                                                                             
│   │   ├── ReminderManager.java                                                                                                             
│   │   ├── Subject.java                                                                                                             
│   ├── system/                                                                                                             
│   │   ├── BudgetSystem.java                                                                                                             
│   │   ├── UI.java                                                                                                             
│   ├── transaction/                                                                                                             
│   │   ├── Expense.java                                                                                                             
│   │   ├── ExpenseManager.java                                                                                                             
│   │   ├── Income.java                                                                                                             
│   │   ├── IncomeManager.java                                                                                                             
│   │   ├── ITransaction.java                                                                                                             
│   │   ├── ITransactionManager.java                                                                                                             
│   ├── user/                                                                                                             
│   │   ├── AuthenticationManager.java                                                                                                             
│   │   ├── CurrentUserObserver.java                                                                                                             
│   │   ├── Observer.java                                                                                                             
│   │   ├── User.java                                                                                                             
│   │   ├── UserManager.java                                                                                                             
│   └── Main.java                                                                                                             
├── pom.xml                                                                                                             
├── README.md                                                                                                             



## Authors

Loai Hataba - https://github.com/Loai-Hataba
Abdullah Mohamed - https://github.com/Abdallah229
Hossam Abdelaziz - https://github.com/HosStdeez
