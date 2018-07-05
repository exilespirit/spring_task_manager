INSERT INTO Users VALUES 
	(NULL, 'Alex Bent', 'alex', '$2a$10$KATcKc6BPAjeok4yDRc.NuTNdIh9xEhqN0rgiYcPPfV.rmKnyst72', 'exilespirit@gmail.com', 'ROLE_USER');
INSERT INTO Users VALUES 
	(NULL, 'Matt Heafy', 'matt', '$2a$10$KATcKc6BPAjeok4yDRc.NuTNdIh9xEhqN0rgiYcPPfV.rmKnyst72', 'exilespirit@gmail.com', 'ROLE_USER');   
INSERT INTO Users VALUES 
	(NULL, 'Administrator', 'admin', '$2a$10$twPGOXKClZHxbaoZwdsD8uOXLpb5jRFYfSy2andAcEQTnrJXWz3EK', 'exilespirit@gmail.com', 'ROLE_ADMIN');
    
INSERT INTO Tasks VALUES
		(NULL, (SELECT UserID from Users WHERE username='alex'), STR_TO_DATE('2018-05-15', '%Y-%m-%d'), 
        'Lorem Ipsum', 'test', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation 
        ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit 
        in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat
        non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');
INSERT INTO Tasks VALUES
		(NULL, (SELECT UserID from Users WHERE username='alex'), STR_TO_DATE('2018-05-15', '%Y-%m-%d'), 
        'Practice', 'test2', 'Play guitar');  
INSERT INTO Tasks VALUES
		(NULL, (SELECT UserID from Users WHERE username='matt'), STR_TO_DATE('2018-05-15', '%Y-%m-%d'), 
        'test3', 'test3', 'test3'); 
INSERT INTO Tasks VALUES
		(NULL, (SELECT UserID from Users WHERE username='matt'), STR_TO_DATE('2018-05-15', '%Y-%m-%d'), 
        'test4', 'test4', 'test4'); 