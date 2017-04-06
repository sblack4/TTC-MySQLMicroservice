DROP schema `catalog` ;

CREATE SCHEMA `catalog` ;

CREATE TABLE `catalog`.`catalog_items` (
  `name` VARCHAR(50) NOT NULL,
  `description` LONGTEXT NULL,
  `image` LONGTEXT NULL,
  `price` DECIMAL(15,2) NULL,
  PRIMARY KEY (`name`));
  
  INSERT INTO `catalog`.`catalog_items`(`name`,`description`,`image`,`price`)VALUES
  ("Application Container Cloud Service",
  "Polyglot Development Platform in Oracle's Cloud",
  "img",
  60.00);
  
    INSERT INTO `catalog`.`catalog_items`(`name`,`description`,`image`,`price`)VALUES
  ("Java Cloud Service",
  "Java EE Development Platform in Oracle's Cloud",
  "img",
  450.00);
  
    INSERT INTO `catalog`.`catalog_items`(`name`,`description`,`image`,`price`)VALUES
  ("Mobile Cloud Service",
  "Mobile Backend as a Service for a Complete Mobile Development Platform in Oracle's Cloud",
  "img",
  2275.00);
  
    INSERT INTO `catalog`.`catalog_items`(`name`,`description`,`image`,`price`)VALUES
  ("Developer Cloud Service",
  "Development Platform hosting all your development tools and services supporting your agile development needs in Oracle' Cloud",
  "img",
  0.00);
  
     INSERT INTO `catalog`.`catalog_items`(`name`,`description`,`image`,`price`)VALUES
  ("Container Cloud Service",
  "Docker host platform to execute your full docker registry in Oracle's Cloud",
  "img",
  75.00);
  
     INSERT INTO `catalog`.`catalog_items`(`name`,`description`,`image`,`price`)VALUES
  ("MySQL Cloud Service",
  "Fully featured MySQL Database in Oracle's Cloud",
  "img",
  130.00);
  
     INSERT INTO `catalog`.`catalog_items`(`name`,`description`,`image`,`price`)VALUES
  ("Database Cloud Service",
  "Fully featured Oracle Database in Oracle's Cloud",
  "img",
  600.00);
  
     INSERT INTO `catalog`.`catalog_items`(`name`,`description`,`image`,`price`)VALUES
  ("Compute Cloud Service",
  "Run on Infrastructure as a service in Oracle's Cloud",
  "img",
  75.00);