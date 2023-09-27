-- prepares a MySQL server for the project

CREATE DATABASE IF NOT EXISTS karate_db;
CREATE USER IF NOT EXISTS 'karate_qa'@'localhost' IDENTIFIED BY 'karate_qa_password';
GRANT ALL PRIVILEGES ON `karate_db`.* TO 'karate_qa'@'localhost';
GRANT SELECT ON `performance_schema`.* TO 'karate_qa'@'localhost';
FLUSH PRIVILEGES;

USE karate_db;
CREATE TABLE IF NOT EXISTS jobs (
    job_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    job_desc varchar(255) NOT NULL,
    min_lvl int NOT NULL,
    max_lvl int NOT NULL
)
