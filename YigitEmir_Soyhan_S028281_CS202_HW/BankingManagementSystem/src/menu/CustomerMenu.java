package menu;

import com.sun.tools.javac.Main;

import java.util.Scanner;
import java.sql.*;

public class CustomerMenu {
    public static void display(Connection connection, Scanner scanner) {
        while (true) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("|      -1  Add New Customer                                   |");
            System.out.println("|      -2  Modify Customer Details                            |");
            System.out.println("|      -3  Delete Customer                                    |");
            System.out.println("|      -4  Search Customer Details                            |");
            System.out.println("|      -5  Exit                                               |");
            System.out.println("-----------------------------------------------------------------");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCustomer(connection, scanner);
                case 2 -> modifyCustomer(connection, scanner);
                case 3 -> deleteCustomer(connection, scanner);
                case 4 -> searchCustomer(connection, scanner);
                case 5 -> MainMenu.display();
                case 6 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }

        }

    }

    public static void addCustomer(Connection connection, Scanner scanner) {
        try{
            System.out.println("Enter SSN(XXX-XX-XXXX):  ");
            String ssn = scanner.nextLine();
            System.out.println("Enter First Name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter Last Name: ");
            String lastName = scanner.nextLine();
            System.out.println("Dial Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter Address: ");
            String address = scanner.nextLine();
            String sql = "INSERT INTO CUSTOMER (customer_ssn, cust_first_name, cust_last_name, phone_num, address) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ssn);
            stmt.setString(2,firstName);
            stmt.setString(3,lastName);
            stmt.setString(4,phoneNumber);
            stmt.setString(5,address);
            stmt.executeUpdate();
            System.out.println("Customer added successfully.");
        }catch (SQLException e) {System.out.println("Something went wrong, please try again." + e.getMessage());}
    }

    public static void modifyCustomer(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter SSN of the customer to be modified: ");
            String ssn = scanner.nextLine();
            System.out.println("Enter New First Name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter NewLast Name: ");
            String lastName = scanner.nextLine();
            System.out.println("Enter New Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter New Address: ");
            String address = scanner.nextLine();
            String sql = "UPDATE CUSTOMER SET cust_first_name = ?, cust_last_name = ?, phone_num = ?, address = ? WHERE customer_ssn = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3,phoneNumber);
            stmt.setString(4,address);
            stmt.setString(5, ssn);
            int updatedorNot = stmt.executeUpdate();
            if (updatedorNot > 0) {
                System.out.println("Customer updated successfully.");
            }
            else System.out.println("There is no such customer in the database.");

        }catch (SQLException e) {System.out.println("Something went wrong, please try again." + e.getMessage());}
    }

    public static void deleteCustomer(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter SSN of customer to delete: ");
            String ssn = scanner.nextLine();

            String sql = "DELETE FROM Customer WHERE customer_ssn=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ssn);
            int deleted = stmt.executeUpdate();

            if (deleted > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    public static void searchCustomer(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter SSN of the customer to search: ");
            String ssn = scanner.nextLine();
            String sql = "SELECT * FROM Customer WHERE customer_ssn=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,ssn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                System.out.println("SSN: " + rs.getString("customer_ssn"));
                System.out.println("First Name: " + rs.getString("cust_first_name"));
                System.out.println("Last Name: " + rs.getString("cust_last_name"));
                System.out.println("Phone Number: " + rs.getString("phone_num"));
                System.out.println("Address: " + rs.getString("address"));
            }else {System.out.println("Customer not found.");}
        } catch (SQLException e) {System.out.println("Something went wrong: " + e.getMessage());}
    }

}

