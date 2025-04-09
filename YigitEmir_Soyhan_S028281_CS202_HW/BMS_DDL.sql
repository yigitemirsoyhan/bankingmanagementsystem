CREATE DATABASE bankDB ; 
USE bankDB;
CREATE TABLE Customer(
    customer_ssn CHAR(11) PRIMARY KEY,
    cust_first_name VARCHAR(80),
    cust_last_name VARCHAR(80),
    phone_num VARCHAR(20),
    address TEXT
);

CREATE TABLE Branch(
    branch_id INT AUTO_INCREMENT PRIMARY KEY,
    branch_name VARCHAR(80),
    location VARCHAR(80)
);

CREATE TABLE Account(
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_ssn CHAR(11),
    branch_id INT,
    balance DECIMAL(15,2) NOT NULL DEFAULT 0  , 
    currency_type ENUM('TRY','USD','EUR','AUD','GBP','JPY','CHF','CAD') NOT NULL ,
    FOREIGN KEY (customer_ssn) REFERENCES Customer(customer_ssn),
    FOREIGN KEY (branch_id) REFERENCES Branch(branch_id)
);

CREATE TABLE Employee(
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_first_name VARCHAR(80),
    emp_last_name VARCHAR(80),
    emp_role ENUM('administrator','loan_officer','investment_advisor'),
    branch_id INT,
    monthly_salary DECIMAL(11,2),
    hire_date DATE,
    FOREIGN KEY (branch_id) REFERENCES Branch(branch_id)
);

CREATE TABLE Card(
    card_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    card_type ENUM('debit','credit'),
    expiry_date DATE,
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

CREATE TABLE Loan(
    loan_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_ssn CHAR(11),
    employee_id INT,
    loan_amount DECIMAL(15,2) NOT NULL,
    loan_currency ENUM('TRY','USD','EUR','AUD','GBP','JPY','CHF','CAD') NOT NULL ,
    interest_rate DECIMAL(5,2),
    FOREIGN KEY (customer_ssn) REFERENCES Customer(customer_ssn),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

CREATE TABLE Transaction(
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    from_account_id INT,
    to_account_id INT,
    transaction_type ENUM('deposit','withdrawal','transfer'),
    transaction_amount DECIMAL(15,2) NOT NULL,
    transaction_currency ENUM('TRY','USD','EUR','AUD','GBP','JPY','CHF','CAD') NOT NULL,
    transaction_date DATETIME,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id),
    FOREIGN KEY (from_account_id) REFERENCES Account(account_id),
    FOREIGN KEY (to_account_id) REFERENCES Account(account_id)
);

CREATE TABLE Investment_Portfolio(
    portfolio_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_ssn CHAR(11),
    FOREIGN KEY (customer_ssn) REFERENCES Customer(customer_ssn)
);

CREATE TABLE Investment(
    investment_id INT AUTO_INCREMENT PRIMARY KEY,
    portfolio_id INT,
    investment_type ENUM('XAU','XAG','Stock','Bond') NOT NULL,
    investment_amount DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (portfolio_id) REFERENCES Investment_Portfolio(portfolio_id)
);
