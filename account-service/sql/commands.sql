CREATE SCHEMA app AUTHORIZATION "accop_app";

CREATE TABLE IF NOT EXISTS app.accouts (
    uuid UUID PRIMARY KEY,
    dt_create TIMESTAMP WITH TIME ZONE,
    dt_update TIMESTAMP WITH TIME ZONE,
    title VARCHAR(255),
    description VARCHAR(255),
    balance double precision,
    type VARCHAR(255),
    currency UUID
);

ALTER TABLE app.accouts
    OWNER TO "accop_app";


CREATE TABLE IF NOT EXISTS app.operation (
    account_id UUID,
    uuid_operation UUID PRIMARY KEY,
    dt_create TIMESTAMP WITH TIME ZONE,
    dt_update TIMESTAMP WITH TIME ZONE,
    date TIMESTAMP WITH TIME ZONE,
    description VARCHAR(255),
    category UUID,
    value VARCHAR(255),
    currency UUID,
    FOREIGN KEY (account_id) REFERENCES app.accouts (uuid)
);

ALTER TABLE app.operation
    OWNER TO "accop_app";