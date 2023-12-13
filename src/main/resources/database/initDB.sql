CREATE TABLE IF NOT EXISTS users (
    user_id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone VARCHAR(11) NOT NULL UNIQUE,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS profiles (
    profile_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    about_me TEXT,
    gender VARCHAR(10) CHECK ( gender in ('male', 'female') ) NOT NULL ,
    age INT CHECK ( age > 0 ) NOT NULL,
    date_of_birth DATE NOT NULL,
    hobbies TEXT,
    avatar BYTEA,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS roles (
    role_id INT PRIMARY KEY,
    role VARCHAR(10) NOT NULL
);
