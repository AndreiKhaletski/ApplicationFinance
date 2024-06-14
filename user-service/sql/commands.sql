CREATE SCHEMA app AUTHORIZATION "final_app";

CREATE TABLE IF NOT EXISTS app.users (
    uuid UUID PRIMARY KEY,
    dt_create TIMESTAMP WITH TIME ZONE,
    dt_update TIMESTAMP WITH TIME ZONE,
    mail VARCHAR(255),
    fio VARCHAR(255),
    role VARCHAR(255),
    status VARCHAR(255),
    password VARCHAR(255),
    code VARCHAR(255)
);

ALTER TABLE app.users
    OWNER TO "final_app";


CREATE TABLE IF NOT EXISTS app.verification (
    mail VARCHAR(255) PRIMARY KEY,
    code VARCHAR(255),
    status VARCHAR(255)
);

ALTER TABLE app.verification
    OWNER TO "final_app";