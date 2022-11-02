
DROP INDEX IF EXISTS users_name_index;

DROP TABLE IF EXISTS users CASCADE;

DROP SEQUENCE IF EXISTS users_id_seq;

CREATE SEQUENCE users_id_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE users
  (
    id INTEGER PRIMARY KEY DEFAULT nextval('users_id_seq'),
    name TEXT
  );

CREATE UNIQUE INDEX users_name_index ON users (lower(name));
