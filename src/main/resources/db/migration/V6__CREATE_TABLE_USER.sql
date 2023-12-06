-- Создание таблицы пользователей
CREATE TABLE IF NOT EXISTS "user" (
                                      id SERIAL PRIMARY KEY,
                                      username VARCHAR(32) UNIQUE NOT NULL,
                                      password VARCHAR(60) NOT NULL,
                                      email VARCHAR(256) UNIQUE NOT NULL,
                                      role VARCHAR(16) DEFAULT 'USER'
);

-- Добавление автоматического инкремента для H2
-- При необходимости вы можете закомментировать этот блок при выполнении на PostgreSQL
-- ALTER TABLE "user" ALTER COLUMN id BIGSERIAL;
