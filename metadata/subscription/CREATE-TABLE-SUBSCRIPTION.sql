CREATE TABLE subscription.subscription (
    id SERIAL PRIMARY KEY,
    expiration_date TIMESTAMP,
    is_active BOOLEAN,
    token_id BIGINT,
    FOREIGN KEY (token_id) REFERENCES vpn.token (id)
);
