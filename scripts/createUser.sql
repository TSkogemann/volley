# creates a database and a user and grants permission to only that database
CREATE DATABASE 'volley';
CREATE USER 'volley'@'localhost' IDENTIFIED BY '0tsea!md3r';
GRANT ALL PRIVILEGES ON volley.* TO 'volley'@'localhost';
FLUSH PRIVILEGES;