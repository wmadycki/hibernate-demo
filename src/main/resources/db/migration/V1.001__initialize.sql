drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence;

CREATE TABLE IF NOT EXISTS s_model
(
    code                            VARCHAR NOT NULL,
    revision                        BIGINT NOT NULL DEFAULT 0,
    name                            VARCHAR
);


CREATE TABLE IF NOT EXISTS s_model_child
(
    code                            VARCHAR NOT NULL,
    revision                        BIGINT NOT NULL DEFAULT 0,
    name                            VARCHAR,
    model_code                      VARCHAR,
    model_revision                  BIGINT

);
