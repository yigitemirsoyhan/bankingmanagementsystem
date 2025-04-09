package menu;

import java.util.Scanner;
import java.sql.*;

public class AccountMenu {
    public static void display(Connection connection, Scanner scanner) {
        while(true) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("| 2. Account Management                                         |");
            System.out.println("|      -1  Add New Account                                      |");
            System.out.println("|      -2  Deposit Money                                        |");
            System.out.println("|      -3  Withdraw Money                                       |");
            System.out.println("|      -4  Transfer Funds                                       |");
            System.out.println("|      -5  Check Account Balance                                |");
            System.out.println("|      -6  Close Account                                        |");
            System.out.println("-----------------------------------------------------------------");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addAccount(connection, scanner);
                case 2 -> depositMoney(connection, scanner);
                case 3 -> withdrawMoney(connection, scanner);
                case 4 -> transferFunds(connection, scanner);
                case 5 -> checkAccountBalance(connection, scanner);
                case 6 -> closeAccount(connection, scanner);
                case 7 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }

        }
    }

    public static void addAccount(Connection connection, Scanner scanner) {
        try{
        System.out.print("Enter SSN of the owner of the New Account to Add: ");
        String ssn = scanner.nextLine();
        System.out.print("Enter the Branch ID of the New Account: ");
        int branchid= scanner.nextInt();
        System.out.print("Enter the Currency type of the New Account: ");
        String currency = scanner.nextLine();
        String sql = "INSERT INTO Account(customer_ssn,branch_id, currency_type) VALUES(?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,ssn);
        stmt.setInt(2,branchid);
        stmt.setString(3,currency);
        stmt.executeUpdate();
        System.out.println("Account Added");}catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}

    }


    public static void depositMoney(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Account ID: ");
            String id = scanner.nextLine();
            System.out.println("Enter How Much Money You Want to Deposit: ");
            Double money = scanner.nextDouble();
            String sql = "UPDATE account SET balance = balance + ? WHERE account_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, money);
            stmt.setString(2,id);
            int updatedorNot = stmt.executeUpdate();
            if (updatedorNot > 0) {
                System.out.println("Customer updated successfully.");
            }
            else System.out.println("There is no such customer in the database.");

        }catch (SQLException e) {System.out.println("Something went wrong, please try again." + e.getMessage());}
    }


    public static void withdrawMoney(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Account ID: ");
            int id = scanner.nextInt();
            System.out.println("Enter How Much Money You Want to Withdraw: ");
            Double money = scanner.nextDouble();
            String sql = "UPDATE account SET balance = balance - ? WHERE account_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1,money);
            stmt.setInt(2,id);
            stmt.executeUpdate();
            System.out.println("Money withdrew successfully.");
        }catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }


    public static void transferFunds(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Account ID: ");
            int id = scanner.nextInt();
            System.out.println("Enter How Much Money You Want to Transfer: ");
            Double money = scanner.nextDouble();
            System.out.println("Enter Account ID to Transfer: ");
            int transferid = scanner.nextInt();
            String sql1 = "UPDATE account SET balance = balance - ? WHERE account_id = ?";
            PreparedStatement stmt1 = connection.prepareStatement(sql1);
            stmt1.setDouble(1,money);
            stmt1.setInt(2,id);
            stmt1.executeUpdate();
            String sql2 = "UPDATE account SET balance = balance + ? WHERE account_id = ?";
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            stmt2.setDouble(1,money);
            stmt2.setInt(2,transferid);
            stmt2.executeUpdate();
            System.out.println("Money transferred successfully.");
        }catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }

    public static void checkAccountBalance(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Account ID: ");
            int id = scanner.nextInt();
            String sql = "SELECT balance,currency_type FROM account WHERE account_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                System.out.println("Balance: " + rs.getDouble("balance") +" " + rs.getString("currency_type"));
            }
        }catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }

    public static void closeAccount(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Account ID to be deleted: ");
            int id = scanner.nextInt();
            String sql = "DELETE FROM account WHERE account_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            int deleted = stmt.executeUpdate();
            if (deleted > 0) {System.out.println("Account deleted successfully.");} else {System.out.println("There exists no such account in the database.");}
        } catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }
}
