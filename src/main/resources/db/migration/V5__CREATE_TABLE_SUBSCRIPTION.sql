-- Создание таблицы подписок
CREATE TABLE IF NOT EXISTS subscription_subscription (
                                                         id BIGSERIAL PRIMARY KEY,
                                                         expiration_date TIMESTAMP,
                                                         is_active BOOLEAN,
                                                         token_id BIGINT,
                                                         FOREIGN KEY (token_id) REFERENCES vpn_token (id)
);

-- Добавление автоматического инкремента для H2
-- При необходимости вы можете закомментировать этот блок при выполнении на PostgreSQL
-- ALTER TABLE subscription_subscription ALTER COLUMN id BIGSERIAL;
