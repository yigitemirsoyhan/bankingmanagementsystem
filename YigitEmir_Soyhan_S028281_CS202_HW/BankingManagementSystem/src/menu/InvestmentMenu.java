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
            System.out.println("-----------------------------------------------------------------");
            System.out.println("-----------Select an Option: ------------------------------------");
            int choice = scanner.nextInt();


            switch (choice) {
                case 1 -> openInvestmentPortfolio(connection, scanner);
                case 2 -> buyInvestment(connection, scanner);
                case 3 -> sellInvestment(connection, scanner);
                case 4 -> viewInvestmentPortfolio(connection, scanner);
                case 5 -> MainMenu.display();
                case 6 -> {
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

        }catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }
    public static void buyInvestment(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Investment Portfolio ID: ");
            int id = scanner.nextInt();
            System.out.print("Enter Investment Type(XAU,XAG,Stock,Bond):  ");
            String type = scanner.next();
            System.out.print("Enter Amount to buy: ");
            double amount = scanner.nextDouble();
            String sql = "INSERT INTO investment(portfolio_id,investment_type,investment_amount) VALUES(?, ?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.setString(2,type);
            stmt.setDouble(3,amount);
            stmt.executeUpdate();
            System.out.print("Investment Portfolio Added Successfully: ");
        }catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }
    public static void sellInvestment(Connection connection, Scanner scanner) {
        try {


        System.out.print("Enter Investment ID: ");
        int id = scanner.nextInt();
        String sql = "DELETE FROM Investment WHERE investment_id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.executeUpdate();
        System.out.print("Investment Deleted Successfully: ");
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
            if (rs.next()){
                System.out.print("Investment Id: " + rs.getInt("investment_id") + "Investment Type and Amount: " + rs.getString("investment_type") + " " + rs.getDouble("investment_amount"));
            } else System.out.print("No Investment Found");
        }catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }

}
