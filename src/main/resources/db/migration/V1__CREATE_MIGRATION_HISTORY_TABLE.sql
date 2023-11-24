-- Создание таблицы истории миграций, если её нет
CREATE TABLE IF NOT EXISTS flyway_schema_history (
     installed_rank INT NOT NULL,
     version VARCHAR(50),
     description VARCHAR(200) NOT NULL,
     type VARCHAR(20) NOT NULL,
     script VARCHAR(1000) NOT NULL,
     checksum INT,
     installed_by VARCHAR(100) NOT NULL,
     installed_on TIMESTAMP NOT NULL,
     execution_time INT NOT NULL,
     success BOOLEAN NOT NULL
);

-- Проверка наличия записи перед вставкой
IF NOT EXISTS (SELECT 1 FROM flyway_schema_history WHERE version = '1.0.0' AND type = 'BASELINE' AND script = 'V1__CREATE_MIGRATION_HISTORY_TABLE.sql') THEN
    -- Вставка базовой миграции в таблицу истории миграций
INSERT INTO flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success)
VALUES (1, '1.0.0', 'Base migration', 'BASELINE', 'V1__CREATE_MIGRATION_HISTORY_TABLE.sql', NULL, 'nesterrovv', CURRENT_TIMESTAMP(), 0, true);
END IF;
