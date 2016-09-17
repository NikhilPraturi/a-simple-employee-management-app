CREATE TABLE EMPLOYEE ( 

    employee_id int,
    name varchar(45), 
     phone_number varchar(45),
     supervisors varchar(45),
    PRIMARY KEY(employee_id) 
);

CREATE SEQUENCE user_id_seq;
ALTER TABLE EMPLOYEE ALTER employee_id SET DEFAULT NEXTVAL('user_id_seq');


INSERT INTO EMPLOYEE (name, phone_number, supervisors) 
VALUES ('Bill Joy','13735864008', 'Beck Ham');

INSERT INTO EMPLOYEE (name, phone_number, supervisors)
VALUES ('Jack Adison','14735864008', 'Bill Joy');

INSERT INTO EMPLOYEE (name, phone_number, supervisors)
VALUES ('Sun Jian','18785864008', 'Mike London');

INSERT INTO EMPLOYEE (name, phone_number, supervisors)
VALUES ('Xiao Ming','877518947', 'Zhang Lei');

CREATE TABLE USERINFO( 
    username varchar(45), 
     password varchar(45),
    PRIMARY KEY(username) 
);

INSERT INTO USERINFO (username, password)
VALUES ('admin','admin');



