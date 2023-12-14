INSERT INTO roles (role_id, name) VALUES
        (1, 'admin'),
        (2, 'user'),
        (3, 'staff'),
        (4, 'developer');

INSERT INTO users (user_id, username, password, role_id, email, phone)
    VALUES
        ('550e8400-e29b-41d4-a716-446655440000', 'user1', 'user1', 1, 'user1@example.com', '1234567890'),
        ('7c9e6679-74c6-4b7f-8b89-900c74e9a689', 'russian_sigma', 'kamen', 2, 'user2@example.com', '0987654321');

INSERT INTO profiles (profile_id, user_id, first_name, last_name, about_me, gender, age, date_of_birth, hobbies, avatar)
VALUES
    ('f47ac10b-58cc-4372-a567-0e02b2c3d479', '550e8400-e29b-41d4-a716-446655440000', 'Ulyana', 'Filippova',  'About user1', 'female', 20, '1990-01-01', 'programming, reading', NULL),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d480', '7c9e6679-74c6-4b7f-8b89-900c74e9a689', 'Dmitriy', 'Vahtin', 'About user2', 'male', 25, '1995-01-01', 'swimming, painting', NULL);