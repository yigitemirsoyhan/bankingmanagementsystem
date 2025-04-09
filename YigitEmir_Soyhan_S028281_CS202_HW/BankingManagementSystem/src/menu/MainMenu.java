package menu;

import db.DBConnection;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;


public class MainMenu {
    public static void display() {
        try (Connection connection = DBConnection.getConnection()) {

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

        while(!exit) {
        System.out.println("------------------- Banking Management System -------------------");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("| 1. Customer Management                                        |");
        System.out.println("| 2. Account Management                                         |");
        System.out.println("| 3. Loan Management                                            |");
        System.out.println("| 4. Investment Management                                      |");
        System.out.println("| 5. Employee Management                                        |");
        System.out.println("| 6. Transaction                                                |");
        System.out.println("| 7. Exit                                                       |");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("| Select an Option                                              |");
        System.out.println("------------------------------------------------------------------");

        int choice = scanner.nextInt();


        switch(choice) {
            case 1:
                CustomerMenu.display(connection, scanner);
                break;
            case 2:
                AccountMenu.display(connection, scanner);
                break;
            case 3:
                LoanMenu.display(connection, scanner);
                break;
            case 4:
                InvestmentMenu.display(connection, scanner);
                break;
            case 5:
                EmployeeMenu.display(connection, scanner);
                break;
            case 6:
                TransactionMenu.display(connection, scanner);
                break;
            case 7:
                exit = true;
                System.out.println("Exiting the system. Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;

        }



        }


    }catch (SQLException e ){System.out.println("Something went wrong" + e.getMessage());}


    }

}
