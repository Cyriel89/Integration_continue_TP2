
DROP SEQUENCE IF EXISTS features_id_seq CASCADE;

CREATE SEQUENCE features_id_seq INCREMENT BY 1 START WITH 1;

DROP TABLE IF EXISTS features CASCADE;

CREATE TABLE features
  (
    id INTEGER PRIMARY KEY DEFAULT nextval('features_id_seq'),
    name TEXT
  );

INSERT INTO features (name) VALUES ('login');
INSERT INTO features (name) VALUES ('logout');
INSERT INTO features (name) VALUES ('adhoc');
INSERT INTO features (name) VALUES ('calculate');
INSERT INTO features (name) VALUES ('memberselection');
INSERT INTO features (name) VALUES ('colorcoding');
INSERT INTO features (name) VALUES ('formatting');
INSERT INTO features (name) VALUES ('zoomin');
INSERT INTO features (name) VALUES ('zoomout');
INSERT INTO features (name) VALUES ('keeponly');
INSERT INTO features (name) VALUES ('removeonly');
INSERT INTO features (name) VALUES ('refresh');
INSERT INTO features (name) VALUES ('submit');
INSERT INTO features (name) VALUES ('clientauthentication');
INSERT INTO features (name) VALUES ('pivot');
INSERT INTO features (name) VALUES ('griddesigner');
INSERT INTO features (name) VALUES ('reportdesigner');
INSERT INTO features (name) VALUES ('alias');
INSERT INTO features (name) VALUES ('interactivechart');
INSERT INTO features (name) VALUES ('loadflatfile');
INSERT INTO features (name) VALUES ('multigrid');
