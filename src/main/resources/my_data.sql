CREATE TABLE CLIENT (
                         ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                         NAME VARCHAR(100)
);

CREATE TABLE PRODUCT (
                         ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                         DESCRIPTION VARCHAR(100),
                         UNIT_PRICE NUMERIC(20,2)
);

CREATE TABLE ORDER (
                        ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                        CLIENT_ID INTEGER REFERENCES CLIENT (ID),
                        ORDER_DATE TIMESTAMP,
                        TOTAL NUMERIC(20,2),
                        STATUS VARCHAR (100)
);

CREATE TABLE ITEM_ORDERED (
                             ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                             ORDER_ID INTEGER REFERENCES ORDER (ID),
                             PRODUCT_ID INTEGER REFERENCES PRODUCT (ID),
                             QUANTITY INTEGER
);