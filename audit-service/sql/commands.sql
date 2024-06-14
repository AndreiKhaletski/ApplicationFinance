CREATE SCHEMA app AUTHORIZATION "audit_app";

CREATE TABLE IF NOT EXISTS app.user (
    uuid UUID PRIMARY KEY,
    mail VARCHAR(255),
    fio VARCHAR(255),
    role VARCHAR(255)
);
ALTER TABLE app.user
    OWNER TO "audit_app";


CREATE TABLE IF NOT EXISTS app.audit (
    uuid UUID PRIMARY KEY,
    dt_create TIMESTAMP WITH TIME ZONE,
    id_user UUID,
    text VARCHAR(255),
    type VARCHAR(255),
    id_entity UUID,
    FOREIGN KEY (id_user) REFERENCES app.user(uuid)
);
ALTER TABLE app.audit
    OWNER TO "audit_app";