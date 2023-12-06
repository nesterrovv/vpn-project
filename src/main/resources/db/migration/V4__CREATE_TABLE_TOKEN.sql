-- Создание таблицы токенов
CREATE TABLE IF NOT EXISTS vpn_token (
                                     id BIGSERIAL PRIMARY KEY,
                                     token VARCHAR(255) NOT NULL
);

-- Добавление автоматического инкремента для H2
-- При необходимости вы можете закомментировать этот блок при выполнении на PostgreSQL
-- ALTER TABLE vpn_token ALTER COLUMN id BIGSERIAL;
