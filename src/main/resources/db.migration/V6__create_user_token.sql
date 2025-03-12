CREATE TABLE IF NOT EXISTS user_token_temp (
    id_token VARCHAR(255) PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    expired_time TIMESTAMP NOT NULL
    );
