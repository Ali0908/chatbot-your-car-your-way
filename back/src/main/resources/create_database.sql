-- Create the 'users' table
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

-- Create the 'chat_message' table
CREATE TABLE chat_message (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              content TEXT NOT NULL,
                              timestamp DATETIME NOT NULL,
                              user_id BIGINT,
                              CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL
);