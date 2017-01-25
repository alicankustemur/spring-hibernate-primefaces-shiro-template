CREATE TABLE
    Person
    (
        ID bigint(22) NOT NULL AUTO_INCREMENT,
        NAME VARCHAR(50),
        RECORD_CREATE_TIME TIMESTAMP NULL,
        RECORD_UPDATE_TIME TIMESTAMP NULL,
        RECORD_IS_DELETED int,
        RECORD_CREATE_USER_ID tinytext,
        RECORD_UPDATE_USER_ID tinytext,
        PRIMARY KEY (id)
    );

INSERT INTO Person (NAME,RECORD_IS_DELETED) VALUES ("Ali Can",0);
INSERT INTO Person (NAME,RECORD_IS_DELETED) VALUES ("Ozcan",0);
INSERT INTO Person (NAME,RECORD_IS_DELETED) VALUES ("Eda",0);
INSERT INTO Person (NAME,RECORD_IS_DELETED) VALUES ("Elanur",0);


CREATE TABLE
    User
    (
        ID bigint(22) NOT NULL AUTO_INCREMENT,
        NAME VARCHAR(255),
		PASSWORD VARCHAR(255),
		ROLE VARCHAR(255),
        RECORD_CREATE_TIME TIMESTAMP NULL,
        RECORD_UPDATE_TIME TIMESTAMP NULL,
        RECORD_IS_DELETED int,
        RECORD_CREATE_USER_ID tinytext,
        RECORD_UPDATE_USER_ID tinytext,
        PRIMARY KEY (id)
    );

INSERT INTO User (NAME,PASSWORD,ROLE,RECORD_IS_DELETED) VALUES ("alicankustemur","12345",1,0);

CREATE TABLE
    Role
    (
        ID bigint(22) NOT NULL AUTO_INCREMENT,
        NAME VARCHAR(255),
        RECORD_CREATE_TIME TIMESTAMP NULL,
        RECORD_UPDATE_TIME TIMESTAMP NULL,
        RECORD_IS_DELETED int,
        RECORD_CREATE_USER_ID tinytext,
        RECORD_UPDATE_USER_ID tinytext,
        PRIMARY KEY (id)
    );

INSERT INTO Role (NAME,RECORD_IS_DELETED) VALUES ("admin",0);
INSERT INTO Role (NAME,RECORD_IS_DELETED) VALUES ("guest",0);

