USE MovesLikeSwagger_MeganM;

CREATE TABLE Capabilities (
	capability_id int PRIMARY KEY AUTO_INCREMENT,
    capability_name VARCHAR(100)
);

INSERT INTO Capabilities (capability_name) VALUES ('Engineering');
INSERT INTO Capabilities (capability_name) VALUES ('Experience Design');
INSERT INTO Capabilities (capability_name) VALUES ('Cyber Security');

ALTER TABLE JobRoles ADD capability_id int;
ALTER TABLE JobRoles ADD FOREIGN KEY (capability_id) REFERENCES Capabilities(id);

UPDATE JobRoles SET capability_id = 1
WHERE job_id<=3;

INSERT INTO JobRoles (job_name, job_specification, sharepoint_link, capability_id, bandlevel_id)
VALUES ("UX Designer","As an experience UX Designer (Consultant) at Kainos, you will deliver intuitive "
+"service experiences based on user needs and design principles. You will be passionate about needs-based "
+"design and an advocate for design thinking and service design. You’ll manage, coach and develop a small "
+"number of staff, with a focus on managing employee performance and assisting in their career development."
+"You will be part of and supported by our growing Experience Design capability creating exemplary digital "
+"services. We are supportive, collaborative, talented, and hugely passionate about user-centred design. We "
+"are not about marketing and dark patterns; we are all about making a positive, measurable impact on millions "
+"of people through quality products and services.","https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications"
+"/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FExperience%20Design%2FJob%20Specification%20%2D%20UX%20Designer"
+"%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FExperience%20Design&p=true&ga=1",2,1),
("Security Engineer","As a Security Engineer, you will work in close collaboration with our technology teams to "
+"design and implement secure, cloud-based software solutions for our clients. Working as part of a multi-disciplinary "
+"Agile team, you will implement DevSecOps practices throughout the software development lifecycle, embedding security practices "
+"(e.g. vulnerability management, threat modelling etc.) and automating security artifact generation (e.g. secret scanning, "
+"container security, SAST, DAST etc.). You will provide subject matter expertise in application security or cloud security – "
+"sharing knowledge on threats and vulnerabilities, identifying appropriate security controls, and increasing cyber security "
+"awareness within teams. ","https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?"
+"id=%2Fpeople%2FJob%20Specifications%2FCyber%20Security%2FJob%20profile%20%2D%20Security%20Engineer%20%28Associate%29%2Epdf&"
+"parent=%2Fpeople%2FJob%20Specifications%2FCyber%20Security&p=true&ga=1",3,1);;