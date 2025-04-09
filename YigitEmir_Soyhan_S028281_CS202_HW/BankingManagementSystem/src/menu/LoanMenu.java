package menu;

import java.sql.*;

import java.util.Scanner;

public class LoanMenu {

    public static void display(Connection connection, Scanner scanner) {
        while (true) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("| 3. Loan Management                                            |");
            System.out.println("|      -3.1  Apply For a Loan                                   |");
            System.out.println("|      -3.2   View Loans                                        |");
            System.out.println("-----------------------------------------------------------------");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1-> viewLoans(connection, scanner);
            }
        }

    }
    public static void viewLoans(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter the Customer SSN");
            String ssn = scanner.next();
            String sql = "SELECT * FROM Loans WHERE customer_snn = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,ssn);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println("Loan ID: " + rs.getInt("loan_id") + "Employee ID: " + rs.getInt("employee_id") + "Loan Amount: "  + rs.getDouble("loan_amount") + " " + rs.getString("loan_currency") + "Interest Rate: " + rs.getDouble("interest_rate"));
            }
            System.out.println("All Loans of the Customer has shown above.");
        }catch(SQLException e){System.out.println("Something went wrong" + e.getMessage());}
    }
}
