USE MovesLikeSwagger_MeganM;

-- add job_specification column attribute to JobRoles table
ALTER TABLE JobRoles
	ADD COLUMN job_specification TEXT
    AFTER job_name;

-- add sharepoint_link column attribute to JobRoles table
ALTER TABLE JobRoles
    ADD COLUMN sharepoint_link TEXT
    AFTER job_specification;

-- update current job roles to give a job_specification and sharepoint_link

UPDATE JobRoles
SET job_name = 'Trainee Software Engineer', 
job_specification = 'As a Trainee Software Engineer with Kainos, you will work on projects where you can make
a real difference to people’s lives – the lives of people you know. After taking part in our
award-winning, seven-week Engineering Academy, you will then join one of our many
project teams, to learn from our experienced developers, project managers and
customer-facing staff. You’ll have great support and mentoring, balanced with the
experience of being given real, meaningful work to do, to help you truly develop both
technically and professionally.', 
sharepoint_link = 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Trainee%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1'
WHERE job_id = 1;

UPDATE JobRoles
SET job_name = 'Associate Support Technician', 
job_specification = 'As a Support Technician (Associate) in Kainos, you’ll be responsible for analysing and
solving complicated technical issues. You will adhere to ITIL standards and will participate
fully in the Incident management lifecycle. You will work closely with clients, internal
teams and 3rd Party vendors to ensure that problems are resolved. You’ll do this whilst
learning about new approaches, with talented colleagues that will help you to learn,
develop and grow.', 
sharepoint_link = 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Support%20Technician%20%28As%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering'
WHERE job_id = 2;

UPDATE JobRoles
SET job_name = 'Associate Front-End Engineer', 
job_specification = 'As a Front-end Engineer in Kainos, you will have the opportunity to use your expertise in
developing high quality user interface solutions which delight our customers and impact
the lives of users worldwide.
The projects you will join are varied, and often highly visible. You will be working in fastpaced, agile environments, so it is important for you to make sound, reasoned decisions,
and recommendations on front-end and user interfaces with your colleagues.
You are determined, flexible and always constructive; proactive in improving things and
are always inclusive and respectful in your interactions with your team. You will be working
alongside talented, diverse, enthusiastic colleagues, who will help you learn and develop
as you, in turn, mentor those around you.', 
sharepoint_link = 'https://kainossoftwareltd.sharepoint.com/sites/PeopleTeam-SharedDrive/Shared%20Documents/Forms/AllItems.aspx?id=%2Fsites%2FPeopleTeam%2DSharedDrive%2FShared%20Documents%2FPeople%20Team%20Shared%20Drive%2FOrganisational%20Development%20%26%20Learning%2FCareer%20Lattice%2FApproved%20Job%20Profiles%2FEngineering%2FEngineering%2FJob%20Profile%20%2D%20Front%2DEnd%20Engineer%20%28A%29%2Epdf&parent=%2Fsites%2FPeopleTeam%2DSharedDrive%2FShared%20Documents%2FPeople%20Team%20Shared%20Drive%2FOrganisational%20Development%20%26%20Learning%2FCareer%20Lattice%2FApproved%20Job%20Profiles%2FEngineering%2FEngineering&p=true&ga=1'
WHERE job_id = 3;

-- view updated job roles 
SELECT * FROM JobRoles;



