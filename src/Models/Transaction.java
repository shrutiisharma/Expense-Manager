package Models;

public class Transaction {

    //parameters
    String date;                        //date of transaction
    double amount;                      //amount of transaction
    String category;                    //category of transaction eg. Shopping, Food, Salary
    int type;                           //type of transaction
    String note;                        //optional note


    public Transaction(String date, double amount, String category, int type, String note) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.note = note;
    }

    @Override
    public String toString() {

        if (type == TransactionType.TYPE_IN) {
            return (Colour.GREEN + "Income       " + date + "       ₹" + amount + "           " + category + "         " + note + Colour.RESET );
        }
        else {
            return (Colour.RED + "Expense      " + date + "       ₹" + amount + "           " + category + "          " + note + Colour.RESET);
        }
    }
}
