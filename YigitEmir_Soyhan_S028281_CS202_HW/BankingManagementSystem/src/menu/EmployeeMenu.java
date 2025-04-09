package menu;

import java.util.Scanner;
import java.sql.*;
public class EmployeeMenu {
    public static void display(Connection connection, Scanner scanner) {
        while (true) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("| 5. Employee Management                                        |");
            System.out.println("|      -1  Add New Employee                                     |");
            System.out.println("|      -2  Modify Employee Details                              |");
            System.out.println("|      -3  Delete Employee                                      |");
            System.out.println("|      -4  View Employee Information                            |");
            System.out.println("|      -5  Exit                                                 |");
            System.out.println("-----------------------------------------------------------------");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addNewEmployee(connection, scanner);
                case 2 -> modifyEmployee(connection, scanner);
                case 3 -> deleteEmployee(connection, scanner);
                case 4 -> viewEmployee(connection, scanner);
                case 5 -> MainMenu.display();
                case 6 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }

        }
        }

    public static void addNewEmployee(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter New Employee First Name: ");
            String firstName = scanner.next();
            System.out.println("Enter New Employee Last Name: ");
            String lastName = scanner.next();
            System.out.println("Enter New Employee Role(administrator,loan_officer,investment_advisor): ");
            String role = scanner.next().toLowerCase();
            System.out.println("Enter New Employee Branch ID: ");
            int branchID = scanner.nextInt();
            System.out.println("Enter New Employee Monthly Salary: ");
            double monthlySalary = scanner.nextDouble();
            String sql = "INSERT INTO employee (emp_first_name,emp_last_name,emp_role,branch_id,monthly_salary, hire_date) VALUES (?,?,?,?,?, CURRENT_DATE)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,firstName);
            stmt.setString(2,lastName);
            stmt.setString(3,role);
            stmt.setInt(4,branchID);
            stmt.setDouble(5,monthlySalary);
            stmt.executeUpdate();
            System.out.println("Employee Added Successfully");
        }catch (SQLException e) {System.out.println("Something went wrong" + e.getMessage());}
    }

    public static void modifyEmployee(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Employee ID to be Modified: ");
            int employeeID = scanner.nextInt();
            System.out.println("Enter the New First Name: ");
            String firstName = scanner.next();
            System.out.println("Enter the New Last Name: ");
            String lastName = scanner.next();
            System.out.println("Enter the New Role: ");
            String role = scanner.next();
            System.out.println("Enter the New Branch ID: ");
            int branchID = scanner.nextInt();
            System.out.println("Enter the New Monthly Salary: ");
            double monthlySalary = scanner.nextDouble();
            String sql = "UPDATE employee SET emp_first_name = ?, emp_last_name = ?, emp_role = ?, branch_id = ? , monthly_salary = ? WHERE employee_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,firstName);
            stmt.setString(2,lastName);
            stmt.setString(3,role);
            stmt.setInt(4,branchID);
            stmt.setDouble(5,monthlySalary);
            stmt.setInt(6,employeeID);
            stmt.executeUpdate();
            System.out.println("Employee Modified Successfully");

        }catch (SQLException e ){System.out.println("Something went wrong" + e.getMessage());}
    }
    public static void deleteEmployee(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Employee ID to be Deleted: ");
            int employeeID = scanner.nextInt();
            String sql = "DELETE FROM employee WHERE employee_id = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,employeeID);
            stmt.executeUpdate();
            System.out.println("Employee Deleted Successfully");
        }catch (SQLException e ){System.out.println("Something went wrong" + e.getMessage());}
    }
    public static void viewEmployee(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Employee ID to be Viewed: ");
            int employeeID = scanner.nextInt();
            String sql = "SELECT * FROM employee WHERE employee_id = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,employeeID);
            stmt.executeUpdate();
            System.out.println("Employee View Successfully");
        }catch (SQLException e ){System.out.println("Something went wrong" + e.getMessage());}
    }
}

