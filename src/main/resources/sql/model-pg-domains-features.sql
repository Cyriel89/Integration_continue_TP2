
DROP TABLE IF EXISTS domains_features;

CREATE TABLE domains_features
  (
    domainId INTEGER REFERENCES domains ON DELETE CASCADE,
    featureId INTEGER REFERENCES features ON DELETE CASCADE
  );
