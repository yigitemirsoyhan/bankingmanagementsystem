package menu;

import java.util.Locale;
import java.util.Scanner;
import java.sql.*;

public class InvestmentMenu {
    public static void display(Connection connection, Scanner scanner) {
        while(true) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("| 4. Investment Management                                      |");
            System.out.println("|      -1  Open Investment Portfolio                            |");
            System.out.println("|      -2  Buy Investments                                      |");
            System.out.println("|      -3  Sell Investments                                     |");
            System.out.println("|      -4  View Investment Portfolio                            |");
            System.out.println("|      -5  Exit                                                 |");
            System.out.println("-----------------------------------------------------------------");

            int choice = scanner.nextInt();


            switch (choice) {
                case 1 -> openInvestmentPortfolio(connection, scanner);
                case 2 -> buyInvestment(connection, scanner);
                case 3 -> sellInvestment(connection, scanner);
                case 4 -> viewInvestmentPortfolio(connection, scanner);
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }


    }

    public static void openInvestmentPortfolio(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Customer SSN: ");
            String ssn = scanner.next();
            String sql = "INSERT INTO investment_portfolio(customer_ssn) VALUES(?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,ssn);
            System.out.print("Investment Portfolio Added Successfully: ");

        }catch (SQLException e) {System.out.println("Something went wrong " + e.getMessage());}
    }
    public static void buyInvestment(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Investment Portfolio ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Investment Type(XAU,XAG,Stock,Bond):  ");
            String type = scanner.nextLine().trim();
            System.out.print("Enter Amount to buy: ");
            double amount = scanner.nextDouble();

            String checkSql= "SELECT investment_amount from investment WHERE portfolio_id = ? and investment_type = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setDouble(1,id);
            checkStmt.setString(2,type);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()){
                double current_amount = rs.getDouble("investment_amount");
                double new_amount = current_amount + amount;
                String updateSQL = "UPDATE investment SET investment_amount = ? WHERE portfolio_id = ? AND investment_type = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateSQL);
                updateStmt.setDouble(1,new_amount);
                updateStmt.setInt(2,id);
                updateStmt.setString(3,type);
                updateStmt.executeUpdate();
                System.out.println("Investment updated successfully. New amount: " + new_amount);
            }
            else{
            String sql = "INSERT INTO investment(portfolio_id,investment_type,investment_amount) VALUES(?, ?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.setString(2,type);
            stmt.setDouble(3,amount);
            stmt.executeUpdate();
            System.out.print("Investment Portfolio Added Successfully: ");
            }
        }
        catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }

    public static void sellInvestment(Connection connection, Scanner scanner) {
        try {


        System.out.println("Enter Investment ID: ");
        int id = scanner.nextInt();
        System.out.println("Type the amount you want to sell: ");
        double amount = scanner.nextDouble();
        String sql = "UPDATE investment SET investment_amount = investment_amount - ? WHERE investment_id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDouble(1,amount);
        stmt.setInt(2,id);
        stmt.executeUpdate();
        System.out.print("Investment Sold Successfully: ");
        } catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }
    public static void viewInvestmentPortfolio(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Investment Portfolio ID: ");
            int id = scanner.nextInt();
            String sql = "SELECT investment_id, investment_type,investment_amount FROM investment WHERE portfolio_id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            boolean found = false;
            while(rs.next()){
                found = true;
                System.out.print("Investment Id: " + rs.getInt("investment_id") +"\n"
                        + "Investment Type and Amount: " + rs.getString("investment_type") + " " + rs.getDouble("investment_amount") +"\n");
            }
            if (!found){System.out.print("No Investment Found");}
        }
        catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }

}
