CREATE SCHEMA app
   AUTHORIZATION postgres;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE messages (
    id SERIAL PRIMARY KEY,
    timestamp TIMESTAMP NOT NULL,
    from_user VARCHAR(50) NOT NULL,
    to_user VARCHAR(50) NOT NULL,
    text TEXT NOT NULL,
    FOREIGN KEY (from_user) REFERENCES users(login),
    FOREIGN KEY (to_user) REFERENCES users(login)
);