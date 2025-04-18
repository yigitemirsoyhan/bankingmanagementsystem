package menu;

import java.util.Scanner;
import java.sql.*;

public class TransactionMenu {
    public static void display(Connection connection, Scanner scanner) {
        while (true) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("| 6. Transaction                                                 |");
            System.out.println("|      -1  View Transaction History                              |");
            System.out.println("|      -2  Exit                                                  |");
            System.out.println("-----------------------------------------------------------------");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> viewTransactionHistory(connection,scanner);
                case 2 -> {
                    return;
                }
            }
        }
    }
        public static void viewTransactionHistory(Connection connection, Scanner scanner){
            try {
                System.out.println("Enter the Account ID of the desired transaction you want to view: ");
                int accountID = scanner.nextInt();
                String sql = "SELECT * FROM Transactions WHERE from_account_id = ? or to_account_id = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1,accountID);
                stmt.setInt(2,accountID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    System.out.println("Transaction ID " + rs.getInt("transaction_id") + " Employee ID" + rs.getInt("employee_id") + " From Account ID" + rs.getInt("from_account_id") + " To Account ID " + rs.getInt("to_account_id") + "Transaction Type"  + rs.getString("transaction_type") + " Transaction Amount" + rs.getDouble("transaction_amount") + " Transaction Currency: " + rs.getString("transaction_currency") + " Transaction Date: " + rs.getDate("transaction_date"));
                }
                System.out.println("All Transaction History shown above.");

            }catch (SQLException e){System.out.println("Something went wrong" + e.getMessage());}
        }

}
