CREATE TABLE "user"
(
    id       SERIAL,
    username VARCHAR(32) UNIQUE NOT NULL,
    password CHAR(60)           NOT NULL,
    email    VARCHAR(256)
)
