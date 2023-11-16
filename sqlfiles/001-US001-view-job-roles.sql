-- use database --
USE MovesLikeSwagger_MeganM;

-- create job roles table
CREATE TABLE JobRoles (
	job_id int PRIMARY KEY AUTO_INCREMENT,
    job_name VARCHAR(100)
);

-- add some data to job roles table
INSERT INTO JobRoles (job_name) VALUES ('Software Engineer');
INSERT INTO JobRoles (job_name) VALUES ('Support Technician');
INSERT INTO JobRoles (job_name) VALUES ('Front-End Engineer');

