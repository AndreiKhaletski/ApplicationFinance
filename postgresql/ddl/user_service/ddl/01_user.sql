\c consolidation

CREATE SCHEMA app AUTHORIZATION "final_app";

CREATE TABLE IF NOT EXISTS app.users (
    uuid UUID PRIMARY KEY,
    dt_create TIMESTAMP WITH TIME ZONE,
    dt_update TIMESTAMP WITH TIME ZONE,
    mail VARCHAR(255),
    fio VARCHAR(255),
    role VARCHAR(255),
    status VARCHAR(255),
    password VARCHAR(255)
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
	
	
INSERT INTO app.users (
    uuid,
    dt_create,
    dt_update,
    mail,
    fio,
    role,
    status,
    password
)
VALUES (
    '00000000-0000-0000-0000-000000000000',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    'admin@gmail.com',
    'Admin Adminovich',
    'ROLE_ADMIN',
    'ACTIVATED',
    '$2a$10$kVx33idOsssroHLhLR7Bgu1WkJQ.N3Cy0Ma3u6Lcy.8GPuxwnbxmq'
);