/* DATA DETAILS */
-- sequence --
DROP SEQUENCE IF EXISTS data_details_seq;

CREATE SEQUENCE data_details_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;

-- table --
DROP TABLE IF EXISTS data_details;

CREATE TABLE data_details(
    id INT PRIMARY KEY NOT NULL,
    data_details_bytes BLOB
);
