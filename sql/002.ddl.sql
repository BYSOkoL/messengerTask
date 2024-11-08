INSERT INTO app.users (login, password, full_name, birth_date, role) VALUES
('admin', 'admin', 'Admin', '1990-10-10', 'ADMIN'),
('user1', 'user', 'User', '1990-01-01', 'USER'),

INSERT INTO app.messages (timestamp, from_user, to_user, text) VALUES
('2023-01-01 10:00:00', 'user1', 'admin', 'Hello'),
