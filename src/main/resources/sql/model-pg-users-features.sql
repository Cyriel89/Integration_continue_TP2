
DROP TABLE IF EXISTS users_features;

CREATE TABLE users_features
  (
    userId INTEGER REFERENCES users ON DELETE CASCADE,
    featureId INTEGER REFERENCES features ON DELETE CASCADE
  );
