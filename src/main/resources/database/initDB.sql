CREATE TABLE IF NOT EXISTS users (
    user_id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    role_id INT NOT NULL,
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
    name VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS messages (
    message_id INT PRIMARY KEY,
    chat_id INT NOT NULL,
    sender_id UUID NOT NULL,
    receiver_id UUID NOT NULL,
    message_text TEXT NOT NULL,
    message_time TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS chats (
    chat_id INT PRIMARY KEY,
    first_user_id UUID NOT NULL,
    second_user_id UUID NOT NULL,
    last_message_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS users_chats (
    user_id UUID REFERENCES users(user_id),
    chat_id INT REFERENCES chats(id),
    PRIMARY KEY (user_id, chat_id)
);

CREATE TABLE IF NOT EXISTS privileges (
    privilege_id INT PRIMARY KEY,
    name VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles_privileges (
    role_id INT NOT NULL REFERENCES roles(role_id),
    privilege_id INT NOT NULL REFERENCES privileges(privilege_id),
    PRIMARY KEY (role_id, privilege_id)
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id UUID NOT NULL REFERENCES users(user_id),
    role_id INT NOT NULL REFERENCES roles(role_id),
    PRIMARY KEY (user_id, role_id)
);
