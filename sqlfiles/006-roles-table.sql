CREATE TABLE IF NOT EXISTS Roles (
	role_id int PRIMARY KEY AUTO_INCREMENT,
	role_name VARCHAR(50) NOT NULL,
	UNIQUE(role_name)
);

INSERT INTO Roles(role_id,role_name) VALUES
(1,"User"),
(2,"Admin");

ALTER TABLE Users ADD COLUMN role_id INT NOT NULL DEFAULT 1;
ALTER TABLE Users ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES Roles(role_id);