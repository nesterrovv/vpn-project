CREATE TABLE IF NOT EXISTS subscription_account (
                                                    id BIGSERIAL PRIMARY KEY,
                                                    username VARCHAR(255),
                                                    is_main_account BOOLEAN
);
