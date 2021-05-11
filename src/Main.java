
import Models.Colour;
import Models.TransactionOperations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        TransactionOperations transactionOperations = new TransactionOperations();


        System.out.print(Colour.BLUE + "WELCOME!" + Colour.RESET);

        String operations = Colour.YELLOW + "\nFollowing are the operations you can perform using this application:\n" + Colour.RESET +
                "0: Exit the Application.\n" +
                "1: Add a New Transaction.\n" +
                "2: Edit a Transaction.\n" +
                "3: Delete a Transaction.\n" +
                "4: View all Transactions.\n" +
                "5: Filter the Transactions by month.\n" +
                Colour.YELLOW + "Select the operation you want to perform: " + Colour.RESET;

        while (true) {

            System.out.print(operations);
            int choice = input.nextInt();
            System.out.println();

            switch (choice)
            {
                case 0:
                {
                    System.out.println(Colour.PURPLE + "You exit the application." + Colour.RESET);
                    return;
                }

                case 1:
                {
                    System.out.println(Colour.PURPLE + "Add a New Transaction:" + Colour.RESET);
                    transactionOperations.addTransaction();
                    break;
                }

                case 2:
                {
                    System.out.println(Colour.PURPLE + "Edit a Transaction:" + Colour.RESET);
                    transactionOperations.editTransaction();
                    break;
                }

                case 3:
                {
                    System.out.println(Colour.PURPLE + "Delete a Transaction:" + Colour.RESET);
                    transactionOperations.deleteTransaction();
                    break;
                }

                case 4:
                {
                    System.out.println(Colour.PURPLE + "View all Transactions:" + Colour.RESET);
                    transactionOperations.viewTransactions();
                    break;
                }

                case 5:
                {
                    System.out.println(Colour.PURPLE + "Filter the Transactions by Month:" + Colour.RESET);
                    transactionOperations.filterTransactions();
                    break;
                }

                default:
                    System.out.println(Colour.RED + "ERROR! You entered invalid input. Please select from 0 to 5." + Colour.RESET);

            }
        }
    }
}
