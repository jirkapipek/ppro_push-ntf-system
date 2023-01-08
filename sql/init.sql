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

CREATE TABLE ppro.investment (
    investment_id character varying(255) NOT NULL,
    buy_currency character varying(3),
    buy_price double precision,
    category character varying(255),
    created timestamp without time zone NOT NULL,
    sell_currency character varying(3),
    sell_price double precision,
    type character varying(255),
    updated timestamp without time zone NOT NULL
);

ALTER TABLE ppro.investment OWNER TO postgres;

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

ALTER TABLE ONLY ppro.investment
    ADD CONSTRAINT investment_pkey PRIMARY KEY (investment_id);

ALTER TABLE ONLY ppro.party
    ADD CONSTRAINT party_pkey PRIMARY KEY (party_id);

ALTER TABLE ONLY ppro.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);

ALTER TABLE ONLY ppro.document
    ADD CONSTRAINT fka1ysol195176hl4fln98yhfeo FOREIGN KEY (party_id) REFERENCES ppro.party(party_id) ON DELETE CASCADE;

ALTER TABLE ppro.party REPLICA IDENTITY FULL;

ALTER TABLE ppro.product REPLICA IDENTITY FULL;

ALTER TABLE ppro.investment REPLICA IDENTITY FULL;

ALTER TABLE ppro.document REPLICA IDENTITY FULL;
