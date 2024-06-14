FROM postgres:16.3-alpine3.19

ADD /ddl/account_service/ddl/00_account.sql /docker-entrypoint-initdb.d/
ADD /ddl/audit_service/ddl/00_audit.sql /docker-entrypoint-initdb.d/
ADD /ddl/classifier_service/ddl/00_classifier.sql /docker-entrypoint-initdb.d/
ADD /ddl/user_service/ddl/00_user.sql /docker-entrypoint-initdb.d/

ADD /ddl/account_service/ddl/01_account.sql /docker-entrypoint-initdb.d/
ADD /ddl/audit_service/ddl/01_audit.sql /docker-entrypoint-initdb.d/
ADD /ddl/classifier_service/ddl/01_classifier.sql /docker-entrypoint-initdb.d/
ADD /ddl/user_service/ddl/01_user.sql /docker-entrypoint-initdb.d/