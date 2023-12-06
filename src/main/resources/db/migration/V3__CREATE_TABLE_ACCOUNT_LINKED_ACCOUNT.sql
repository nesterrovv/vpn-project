-- Создание таблицы связанных аккаунтов
CREATE TABLE IF NOT EXISTS subscription_account_linked_account (
                                                                   id BIGSERIAL PRIMARY KEY,
                                                                   main_account_id BIGINT,
                                                                   linked_account_id BIGINT,
                                                                   FOREIGN KEY (main_account_id) REFERENCES subscription_account (id),
                                                                   FOREIGN KEY (linked_account_id) REFERENCES subscription_account (id)
);

-- Добавление автоматического инкремента для H2
-- При необходимости вы можете закомментировать этот блок при выполнении на PostgreSQL
-- ALTER TABLE subscription_account_linked_account ALTER COLUMN id BIGINT;
