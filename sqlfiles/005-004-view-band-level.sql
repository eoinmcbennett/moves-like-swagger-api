CREATE TABLE IF NOT EXISTS BandLevel (
	bandlevel_id int PRIMARY KEY AUTO_INCREMENT,
    band_name VARCHAR(70) NOT NULL
);

INSERT INTO BandLevel(bandlevel_id,band_name) VALUES (1,"Trainee");

ALTER TABLE JobRoles ADD COLUMN bandlevel_id INT NOT NULL DEFAULT 1;
ALTER TABLE JobRoles ADD CONSTRAINT fk_bandlevel_id FOREIGN KEY (bandlevel_id) REFERENCES BandLevel(bandlevel_id);