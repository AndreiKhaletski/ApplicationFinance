\c classifier

CREATE SCHEMA app AUTHORIZATION "classifier_app";

CREATE TABLE IF NOT EXISTS app.currency (
    uuid UUID PRIMARY KEY,
    dt_create TIMESTAMP WITH TIME ZONE,
    dt_update TIMESTAMP WITH TIME ZONE,
    title VARCHAR(255),
    description VARCHAR(255)
);

ALTER TABLE app.currency
    OWNER TO "classifier_app";

CREATE TABLE IF NOT EXISTS app.category (
    uuid UUID PRIMARY KEY,
    dt_create TIMESTAMP WITH TIME ZONE,
    dt_update TIMESTAMP WITH TIME ZONE,
    title VARCHAR(255)
);

ALTER TABLE app.category
    OWNER TO "classifier_app";