DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM users)
THEN
    INSERT INTO users (user_id, username, password, email, phone, role)
    VALUES
        ('550e8400-e29b-41d4-a716-446655440000', 'user1', 'user1', 'user1@example.com', '1234567890', 'regular'),
        ('7c9e6679-74c6-4b7f-8b89-900c74e9a689', 'user2', 'user2', 'user2@example.com', '0987654321', 'admin');

    INSERT INTO profiles (profile_id, user_id, about_me, gender, age, date_of_born, hobbies, avatar)
    VALUES
        ('f47ac10b-58cc-4372-a567-0e02b2c3d479', '550e8400-e29b-41d4-a716-446655440000', 'About user1', 'male', 30, '1990-01-01', 'programming, reading', NULL),
        ('f47ac10b-58cc-4372-a567-0e02b2c3d480', '7c9e6679-74c6-4b7f-8b89-900c74e9a689', 'About user2', 'female', 25, '1995-01-01', 'swimming, painting', NULL);
    END IF;
END $$