CREATE SCHEMA IF NOT EXISTS ppro AUTHORIZATION postgres;
SET SCHEMA 'ppro';

CREATE TABLE ppro.document (
    document_id character varying(255) NOT NULL,
    created timestamp without time zone NOT NULL,
    description character varying(255),
    document_language character varying(3),
    filename character varying(255),
    format_code character varying(255),
    party_id character varying(255)
);

ALTER TABLE ppro.document OWNER TO postgres;

CREATE TABLE ppro.party (
    party_id character varying(255) NOT NULL,
    created timestamp without time zone NOT NULL,
    firstname character varying(255),
    ico character varying(8),
    lastname character varying(255),
    reg_status character varying(255)
);

ALTER TABLE ppro.party OWNER TO postgres;

CREATE TABLE ppro.product (
    product_id character varying(255) NOT NULL,
    created timestamp without time zone NOT NULL,
    currency character varying(3),
    price double precision,
    product_code character varying(9),
    product_name character varying(255)
);

ALTER TABLE ppro.product OWNER TO postgres;

ALTER TABLE ONLY ppro.document
    ADD CONSTRAINT document_pkey PRIMARY KEY (document_id);

ALTER TABLE ONLY ppro.party
    ADD CONSTRAINT party_pkey PRIMARY KEY (party_id);

ALTER TABLE ONLY ppro.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);

ALTER TABLE ONLY ppro.document
    ADD CONSTRAINT fka1ysol195176hl4fln98yhfeo FOREIGN KEY (party_id) REFERENCES ppro.party(party_id);
