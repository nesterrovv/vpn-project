CREATE TABLE subscription.account (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    is_main_account BOOLEAN
);
