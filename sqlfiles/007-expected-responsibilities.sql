-- use database --
USE MovesLikeSwagger_MeganM;

-- create responsibilities table
CREATE TABLE Responsibilities (
    responsibility_id INT PRIMARY KEY AUTO_INCREMENT,
    responsibility_desc VARCHAR(255) NOT NULL
);

-- add some data to the responsibilities table
-- data includes common responsibilities among job roles to portray many-to-many relationships
INSERT INTO Responsibilities (responsibility_desc) VALUES ('Contribute to developing high quality solutions that will impact the lives of users worldwide.');
INSERT INTO Responsibilities (responsibility_desc) VALUES ('Complying with all confidentiality NDAs to ensure security of information at all times.');
INSERT INTO Responsibilities (responsibility_desc) VALUES ('You\'ll work as part of a team to solve problems and produce innovative software solutions.');
INSERT INTO Responsibilities (responsibility_desc) VALUES ('Work with other developers by working through designs and user stories.');
INSERT INTO Responsibilities (responsibility_desc) VALUES ('Provide day-to-day technical support and consultancy to clients.');
INSERT INTO Responsibilities (responsibility_desc) VALUES ('Contributing to service review meetings.');
INSERT INTO Responsibilities (responsibility_desc) VALUES ('Developing user interfaces with responsive design that will support various devices.');
INSERT INTO Responsibilities (responsibility_desc) VALUES ('Optimizing front end code for faster loading times and better performance.');
INSERT INTO Responsibilities (responsibility_desc) VALUES ('Co-ordinates the production of prototypes to meet the needs of the user testing and development.');
INSERT INTO Responsibilities (responsibility_desc) VALUES ('Mentors junior team members and represents UX design across Kainos.');
INSERT INTO Responsibilities (responsibility_desc) VALUES ('Verifying the implementation of security principles and assisting the adoption of cyber security practices.');

-- view responsibilities added to the table
SELECT * FROM Responsibilities;

-- create role_responsibilities link table
CREATE TABLE JobResponsibilities (
    job_id INT,
    responsibility_id INT,
    FOREIGN KEY (job_id) REFERENCES JobRoles(job_id),
    FOREIGN KEY (responsibility_id) REFERENCES Responsibilities(responsibility_id),
    PRIMARY KEY (job_id, responsibility_id)
);

-- Update role responsibilities for each job role
INSERT INTO JobResponsibilities (job_id, responsibility_id)
VALUES
-- Software Engineer Responsibilities:
    (1, 1),
    (1, 3),
    (1, 4),

-- Support Technician Responsibilities:
    (2, 2),
    (2, 5),
    (2, 6),

-- Front End Engineer Responsibilities:
    (3, 1),
    (3, 7),
    (3, 8),

-- UX Designer Responsibilities:
    (4, 1),
    (4, 9),
    (4, 10),

-- Security Engineer Responsibilities:
    (5, 1),
    (5, 2),
    (5, 11);

-- view job responsibilities added to the table
SELECT * FROM JobResponsibilities;