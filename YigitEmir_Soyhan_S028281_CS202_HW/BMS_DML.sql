USE bankDB;

INSERT INTO Customer (customer_ssn, cust_first_name, cust_last_name, phone_num, address) VALUES
('111-11-1111', 'Yigit', 'Soyhan', '507-001-01-01', '123 Yurt St'),
('222-22-2222', 'Al', 'Pacino', '555-100-22-22', '456 Oak St'),
('333-33-3333', 'Millie', 'Brown', '555-100-33-33', '789 Pine St'),
('444-44-4444', 'Sener', 'Sen', '555-102-44-44', '101 Cumhuriyet St'),
('555-55-5555', 'Emma', 'Watson', '555-1005', '202 LalaLand St'),
('666-66-6666', 'Joe', 'Pesci', '555-1006', '303 GoodFellas St'),
('777-77-7777', 'George', 'Clooney', '555-1007', '404 Willow St'),
('888-88-8888', 'Lebron', 'James', '555-1008', '505 Lakers St'),
('999-99-9999', 'Lionel', 'Messi', '555-1009', '606 Goat St'),
('000-00-0000', 'Zeynep', 'Sonmez', '555-1010', '707 Grand Slam St');

INSERT INTO Branch (branch_name, location) VALUES
('Levent1', 'Istanbul'),
('Levent2', 'Istanbul'),
('Bahcelievler', 'Ankara'),
('Fatih', 'Istanbul'),
('Ozyegin', 'Istanbul'),
('Kurtulus', 'Ankara'),
('Ulus', 'Ankara'),
('Konak', 'Ankara'),
('Uzunkopru', 'Edirne'),
('Sarkikaraagac', 'Isparta');

INSERT INTO Account (customer_ssn, branch_id, balance, currency_type) VALUES
('111-11-1111', 1, 1500.00, 'USD'),
('222-22-2222', 2, 2200.50, 'EUR'),
('333-33-3333', 3, 3200.00, 'TRY'),
('444-44-4444', 4, 4500.75, 'AUD'),
('555-55-5555', 5, 5100.00, 'GBP'),
('666-66-6666', 6, 6000.00, 'JPY'),
('777-77-7777', 7, 7200.00, 'CHF'),
('888-88-8888', 8, 8300.00, 'CAD'),
('999-99-9999', 9, 9500.00, 'USD'),
('000-00-0000', 10, 10000.00, 'EUR');

INSERT INTO Employee (emp_first_name, emp_last_name, emp_role, branch_id, monthly_salary, hire_date) VALUES
('Peter', 'Parker', 'administrator', 1, 5000.00, '2020-01-15'),
('Mario', 'Gomez', 'loan_officer', 2, 5500.00, '2021-02-20'),
('Ferdi', 'Kadioglu', 'investment_advisor', 3, 5300.00, '2022-03-10'),
('Ferdi', 'Tayfur', 'administrator', 4, 5100.00, '2021-04-05'),
('Xavi', 'Hernandez', 'loan_officer', 5, 5800.00, '2020-05-12'),
('Dirk', 'Kuyt', 'investment_advisor', 6, 5400.00, '2019-06-30'),
('Aykut', 'Kocaman', 'administrator', 7, 6000.00, '2023-07-25'),
('Ismail', 'Kartal', 'loan_officer', 8, 6200.00, '2018-08-18'),
('Jorge', 'Jesus', 'investment_advisor', 9, 6100.00, '2020-09-09'),
('Aziz', 'Yıldırım', 'administrator', 10, 6300.00, '2023-10-10');

INSERT INTO Card (account_id, card_type, expiry_date) VALUES
(1, 'debit', '2026-12-31'),
(2, 'credit', '2025-11-30'),
(3, 'debit', '2027-10-15'),
(4, 'credit', '2026-09-09'),
(5, 'debit', '2025-08-08'),
(6, 'credit', '2027-07-07'),
(7, 'debit', '2026-06-06'),
(8, 'credit', '2028-05-05'),
(9, 'debit', '2027-04-04'),
(10, 'credit', '2026-03-03');

INSERT INTO Loan (customer_ssn, employee_id, loan_amount, loan_currency, interest_rate) VALUES
('111-11-1111', 2, 10000.00, 'USD', 4.5),
('222-22-2222', 5, 5000.00, 'EUR', 5.0),
('333-33-3333', 8, 7000.00, 'TRY', 6.0),
('444-44-4444', 2, 3000.00, 'AUD', 3.5),
('555-55-5555', 5, 8000.00, 'GBP', 4.0),
('666-66-6666', 8, 9000.00, 'JPY', 4.8),
('777-77-7777', 2, 10000.00, 'CHF', 5.1),
('888-88-8888', 5, 11000.00, 'CAD', 3.9),
('999-99-9999', 8, 6000.00, 'USD', 5.3),
('000-00-0000', 2, 4000.00, 'EUR', 4.7);

INSERT INTO Transaction (employee_id, from_account_id, to_account_id, transaction_type, transaction_amount, transaction_currency, transaction_date) VALUES
(1, 1, 2, 'transfer', 200.00, 'USD', NOW()),
(4, 3, NULL, 'withdrawal', 150.00, 'TRY', NOW()),
(7, NULL, 4, 'deposit', 500.00, 'AUD', NOW()),
(1, 5, 6, 'transfer', 1000.00, 'GBP', NOW()),
(4, NULL, 7, 'deposit', 300.00, 'JPY', NOW()),
(7, 8, NULL, 'withdrawal', 250.00, 'CHF', NOW()),
(1, 9, 10, 'transfer', 800.00, 'EUR', NOW()),
(4, NULL, 1, 'deposit', 100.00, 'USD', NOW()),
(7, 2, NULL, 'withdrawal', 200.00, 'EUR', NOW()),
(1, 6, 5, 'transfer', 700.00, 'GBP', NOW());

INSERT INTO Investment_Portfolio (customer_ssn) VALUES
('111-11-1111'), ('222-22-2222'), ('333-33-3333'), ('444-44-4444'), ('555-55-5555'),
('666-66-6666'), ('777-77-7777'), ('888-88-8888'), ('999-99-9999'), ('000-00-0000');

INSERT INTO Investment (portfolio_id, investment_type, investment_amount) VALUES
(1, 'XAU', 1500.00),
(1, 'Bond', 1200.00),
(2, 'XAG', 1800.00),
(2, 'Stock', 2000.00),
(3, 'Bond', 3000.00),
(4, 'Stock', 5000.00),
(5, 'XAU', 2500.00),
(6, 'XAG', 1000.00),
(7, 'Stock', 2700.00),
(8, 'Bond', 3500.00);