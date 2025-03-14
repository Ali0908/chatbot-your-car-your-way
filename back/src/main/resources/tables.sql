CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);

CREATE TABLE response (
                          id SERIAL PRIMARY KEY,
                          question VARCHAR(255) NOT NULL,
                          answer TEXT NOT NULL
);

CREATE TABLE chat_message (
                              id SERIAL PRIMARY KEY,
                              sender VARCHAR(255) NOT NULL,
                              content TEXT NOT NULL,
                              timestamp TIMESTAMP NOT NULL
);

CREATE TABLE chat_message_response (
                                       chat_message_id BIGINT NOT NULL,
                                       response_id BIGINT NOT NULL,
                                       PRIMARY KEY (chat_message_id, response_id),
                                       FOREIGN KEY (chat_message_id) REFERENCES chat_message(id) ON DELETE CASCADE,
                                       FOREIGN KEY (response_id) REFERENCES response(id) ON DELETE CASCADE
);