
DROP INDEX IF EXISTS domains_name_index;

DROP TABLE IF EXISTS domains CASCADE;

DROP SEQUENCE IF EXISTS domains_id_seq;

CREATE SEQUENCE domains_id_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE domains
  (
    id INTEGER PRIMARY KEY DEFAULT nextval('domains_id_seq'),
    name TEXT
  );

CREATE UNIQUE INDEX domains_name_index ON domains (lower(name));
