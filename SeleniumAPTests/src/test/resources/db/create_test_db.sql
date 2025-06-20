DROP TABLE IF EXISTS test_cases;

CREATE TABLE test_cases (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    login TEXT NOT NULL,
    password TEXT NOT NULL,
    expected_message TEXT NOT NULL
);

INSERT INTO test_cases (login, password, expected_message) VALUES
('abcd@aa.com', '1234W', 'Authentication failed'),
('test@email', '1231AA', 'Invalid email address'),
('test@email.pl', '123', 'Invalid password');