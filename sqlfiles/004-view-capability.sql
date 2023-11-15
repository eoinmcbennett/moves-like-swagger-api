USE MovesLikeSwagger_MeganM;

CREATE TABLE Capabilities (
	capability_id int PRIMARY KEY AUTO_INCREMENT,
    capability_name VARCHAR(100)
);

INSERT INTO Capabilities (capability_name) VALUES ('Engineering');
INSERT INTO Capabilities (capability_name) VALUES ('Workday');
INSERT INTO Capabilities (capability_name) VALUES ('Operations');

ALTER TABLE JobRoles ADD capability_id int;
ALTER TABLE JobRoles ADD FOREIGN KEY (capability_id) REFERENCES Capabilities(id);

UPDATE JobRoles SET capability_id = 1
WHERE job_id<=3;