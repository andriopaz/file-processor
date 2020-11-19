DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS sale_item;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS salesman;
 
CREATE TABLE SALESMAN (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  cpf VARCHAR(20),
  name VARCHAR(250) NOT NULL,
  salary DOUBLE DEFAULT 0,
  
  unique key unique_cpf(cpf)
);

CREATE TABLE CUSTOMER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  cnpj VARCHAR(20) NOT NULL,
  name VARCHAR(250) NOT NULL,
  business_area VARCHAR(250) DEFAULT NULL,
  
  unique key unique_cnpj(cnpj)
);

CREATE TABLE SALE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  salesman_id INT,
  price DOUBLE NOT NULL,
  
  foreign key (salesman_id) references salesman(id)
);