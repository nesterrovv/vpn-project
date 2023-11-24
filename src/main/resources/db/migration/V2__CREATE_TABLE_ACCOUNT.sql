CREATE TABLE IF NOT EXISTS subscription.account (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255),
    is_main_account BOOLEAN
);
