SET client_encoding = 'UTF8';

CREATE SEQUENCE company_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
CREATE TABLE company (
    id bigint NOT NULL DEFAULT nextval('company_id_seq'),
    name character varying(255),
    cif character varying(255),
    enabled boolean not null default true
);