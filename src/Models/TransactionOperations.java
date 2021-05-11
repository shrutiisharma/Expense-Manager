package Models;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TransactionOperations {

    Scanner input = new Scanner(System.in);

    ArrayList<Transaction> transactionsArrayList = new ArrayList<>();
    public HashMap<String, ArrayList<Transaction>> allTransactionsHashMap = new HashMap<>();

//  //add a new transaction
    public void addTransaction(){

        String menu = """
                Choose the Transaction Type:
                0: Go Back
                1: Income
                2: Expense
                Enter your choice: """;

        while(true){

            System.out.print(menu);
            int menuOption = input.nextInt();
            System.out.println();

            //go back option is chosen
            if (menuOption == 0)        return;

            //to add income or expense transaction
            else if (menuOption == 1 || menuOption == 2){

                System.out.print("Enter the Date of Transaction (yyyy-mm-dd): ");
                String date = input.nextLine();

                //make sure that the user must enter something
                while (date.isEmpty()) date = input.nextLine();

                System.out.print("Enter the Amount of Transaction: ₹");
                double amount = input.nextDouble();

                System.out.print("Enter the Category of Transaction: ");
                String category = input.nextLine();

                //make sure that the user must enter something
                while (category.isEmpty()) category = input.nextLine();

                System.out.print("Any Optional Note: ");
                String note = input.nextLine();

                Transaction transaction = new Transaction(date, amount, category, menuOption - 1, note);
                transactionsArrayList.add(transaction);
                LocalDate localDate = LocalDate.parse(transaction.date, DateTimeFormatter.ISO_DATE);

                //key for hashmap
                String key = localDate.getMonth().toString() + " " + localDate.getYear();

                //if particular month of a year already has previous transactions
                if (allTransactionsHashMap.containsKey(key)) {
                    allTransactionsHashMap.get(key).add(transaction);
                }

                //first transaction of a particular month
                else {
                    ArrayList<Transaction> newTransaction = new ArrayList<>();
                    newTransaction.add(transaction);
                    allTransactionsHashMap.put(key, newTransaction);
                }

                System.out.println(Colour.GREEN + "DONE! Transaction Added Successfully." + Colour.RESET);
            }

            //invalid option is chosen
            else System.out.println(Colour.RED + "INVALID Choice! Please choose from 0-2." + Colour.RESET);

            System.out.println();
        }
    }



//  //edit a transaction
    public void editTransaction() {

        String menu = """
                \nChoose the field you want to edit:
                0: Go Back
                1: Transaction Date
                2: Transaction Type
                3: Transaction Amount
                4: Transaction Category
                5: Optional Note
                Enter your choice: """;

        while (true) {

            System.out.print("Enter the Month and the Year (Format: MAY 2021) (Enter 0 to Go Back): ");
            String key = input.nextLine();

            while (key.isEmpty())
                key = input.nextLine();

            //go back option is chosen
            if (key.equals("0")) return;

            ArrayList<Transaction> trans = allTransactionsHashMap.get(key);

            //check if transactions are available or not
            if (trans == null) {
                System.out.println(Colour.RED + "SORRY! No Transactions Available.\n" + Colour.RESET);
                return;
            }

            //printing all the transactions of the month serial wise
            System.out.println("    TYPE           DATE          AMOUNT            CATEGORY         NOTE(optional)");
            for (int i = 0; i < trans.size(); i++) {
                System.out.println((i + 1) + ". " + trans.get(i));
            }

            System.out.print("\nChoose the Transaction you want to edit or Enter '0' to Go Back: ");
            int choice = input.nextInt();

            //go back option is chosen
            if (choice == 0)    continue;

            Transaction transaction = trans.get(choice - 1);

            while (true) {
                System.out.print(menu);
                int menuOption = input.nextInt();
                System.out.println();

                //go back option is chosen
                if (menuOption == 0) break;

                //edit transaction date
                else if (menuOption == 1) {
                    System.out.print("Enter the New Date of Transaction (yyyy-mm-dd): ");
                    String newDate = input.nextLine();

                    //make sure that the user must enter something
                    while (newDate.isEmpty()) newDate = input.nextLine();

                    transaction.date = newDate;

                    System.out.println(Colour.GREEN + "DONE! Date Edited successfully." + Colour.RESET);
                }

                //edit transaction type
                else if (menuOption == 2) {
                    System.out.print("Choose the Transaction Type:\n" +
                            "1: Income\n" +
                            "2: Expense\n");

                    int newType = input.nextInt();

                    transaction.type = newType - 1;

                    System.out.println(Colour.GREEN + "DONE! Transaction Type Edited successfully." + Colour.RESET);
                }

                //edit transaction amount
                else if (menuOption == 3) {
                    System.out.print("Enter the new Amount of Transaction: ₹");

                    int newAmount = input.nextInt();

                    transaction.amount = newAmount;

                    System.out.println(Colour.GREEN + "DONE! Amount Edited successfully." + Colour.RESET);
                }

                //edit transaction category
                else if (menuOption == 4) {
                    System.out.print("Enter the Category of Transaction: ");
                    String newCategory = input.nextLine();

                    //make sure that the user must enter something
                    while (newCategory.isEmpty()) newCategory = input.nextLine();

                    transaction.category = newCategory;

                    System.out.println(Colour.GREEN + "DONE! Category Edited successfully." + Colour.RESET);
                }

                //edit transaction note
                else if (menuOption == 5) {
                    System.out.print("Enter the Note of Transaction: ");
                    String note = input.nextLine();

                    //make sure that the user must enter something
                    while (note.isEmpty()) note = input.nextLine();

                    transaction.note = note;

                    System.out.println(Colour.GREEN + "DONE! Note Edited successfully." + Colour.RESET);
                }

                //invalid choice
                else System.out.println(Colour.RED + "INVALID Choice! Please choose from 0-5." + Colour.RESET);

                System.out.println();
            }
        }
    }



//  //delete a transaction
    public void deleteTransaction() {

        while (true) {
            System.out.print("Enter the Month and the Year (Format: MAY 2021) (Enter 0 to Go Back): ");
            String key = input.nextLine();

            while (key.isEmpty())
                key = input.nextLine();

            //go back option is chosen
            if (key.equals("0")) return;

            ArrayList<Transaction> trans = allTransactionsHashMap.get(key);

            //check if transactions are available or not
            if (trans == null) {
                System.out.println(Colour.RED + "SORRY! No Transactions Available.\n" + Colour.RESET);
                return;
            }

            //printing all the transactions of the month serial wise
            System.out.println("    TYPE           DATE          AMOUNT            CATEGORY         NOTE(optional)");
            for (int i = 0; i < trans.size(); i++) {
                System.out.println((i + 1) + ". " + trans.get(i));
            }

            System.out.print("\nChoose the Transaction you want to Delete or Enter '0' to Go Back: ");
            int choice = input.nextInt();

            //go back option is chosen
            if (choice == 0) continue;

            trans.remove(choice - 1);

            System.out.println(Colour.GREEN + "DONE! Transaction Deleted successfully." + Colour.RESET);
            System.out.println();
        }
    }



//  //view all transactions
    public void viewTransactions(){

        //check if transactions are available or not
        if (transactionsArrayList.isEmpty()) {
            System.out.println(Colour.RED + "SORRY! No Transactions Available.\n" + Colour.RESET);
            return;
        }

        double totalIncome = 0;
        double totalExpense = 0;
        int noOfTransactions = 0;

        System.out.println("    TYPE           DATE          AMOUNT            CATEGORY         NOTE(optional)");
        for (int i = 0; i < transactionsArrayList.size() ; i++){
            System.out.println((i + 1) + ". " + transactionsArrayList.get(i));

            if (transactionsArrayList.get(i).type == 0)     totalIncome += transactionsArrayList.get(i).amount;
            else if (transactionsArrayList.get(i).type == 1)     totalExpense += transactionsArrayList.get(i).amount;

            noOfTransactions++;
        }

        System.out.println();
        System.out.println("Total Transactions = " + noOfTransactions);
        System.out.println("Total Income = ₹" + totalIncome);
        System.out.println("Total Spent = ₹" + totalExpense);
        System.out.println("Saving = ₹(" + (totalIncome - totalExpense) + ")");
        System.out.println();
    }



//  //filter transactions by month
    public void filterTransactions(){

        System.out.print("Enter the Month and the Year (Format: MAY 2021) (Enter 0 to Go Back): ");
        String key = input.nextLine();

        while (key.isEmpty())
            key = input.nextLine();

        //go back option is chosen
        if (key.equals("0")) return;

        ArrayList<Transaction> trans = allTransactionsHashMap.get(key);

        //check if transactions are available or not
        if (trans == null) {
            System.out.println(Colour.RED + "SORRY! No Transactions Available.\n" + Colour.RESET);
            return;
        }

        double totalIncome = 0;
        double totalExpense = 0;
        int noOfTransactions = 0;

        //printing all the transactions of the month serial wise
        System.out.println("    TYPE           DATE          AMOUNT            CATEGORY         NOTE(optional)");
        for (int i = 0; i < trans.size(); i++) {
            System.out.println((i + 1) + ". " + trans.get(i));

            if (transactionsArrayList.get(i).type == 0)     totalIncome += transactionsArrayList.get(i).amount;
            else if (transactionsArrayList.get(i).type == 1)     totalExpense += transactionsArrayList.get(i).amount;

            noOfTransactions++;
        }

        System.out.println();
        System.out.println("Total Transactions = " + noOfTransactions);
        System.out.println("Total Income = ₹" + totalIncome);
        System.out.println("Total Spent = ₹" + totalExpense);
        System.out.println("Saving = ₹(" + (totalIncome - totalExpense) + ")");
        System.out.println();

    }
}
