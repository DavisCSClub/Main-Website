INSERT INTO dcsc_accounts.dcsc_roles VALUES (1, 'SUPER');
INSERT INTO dcsc_accounts.dcsc_roles VALUES (2, 'BASIC');

INSERT INTO dcsc_accounts.dcsc_class VALUES (1, 'CAROUSEL');
INSERT INTO dcsc_accounts.dcsc_class VALUES (2, 'EVENT');
INSERT INTO dcsc_accounts.dcsc_class VALUES (3, 'USER');

INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (1, 1, 15);
INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (1, 2, 15);
INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (1, 3, 15);

INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (2, 1, 1);
INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (2, 2, 1);
INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (2, 3, 1);

INSERT INTO dcsc_accounts.dcsc_profiles VALUES (1, 'Root', 'johnDoe@nowhere.org', 'President', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (2, 'John Doe', 'johnDoe@nowhere.org', 'Title', 'Sample Profile');

-- local development password for these users is "password"
INSERT INTO dcsc_accounts.dcsc_users VALUES (1, 'dcsc', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,1,1 );
INSERT INTO dcsc_accounts.dcsc_users VALUES (2, 'john.doe', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,2);

INSERT INTO dcsc_accounts.dcsc_groups VALUES (1, 'My Group');
INSERT INTO dcsc_accounts.dcsc_groups VALUES (2, 'Tutoring Committee');

INSERT INTO dcsc_accounts.dcsc_user_groups VALUES (1,1, TRUE);
INSERT INTO dcsc_accounts.dcsc_user_groups VALUES (1,2, TRUE);
INSERT INTO dcsc_accounts.dcsc_user_groups VALUES (2,2, FALSE);

INSERT INTO public.dcsc_events VALUES (1, 'Sample Event', 'Sample Description', 'tag', '2016-01-02', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);

INSERT INTO public.dcsc_images VALUES (1, 'Sample Image', NULL, '/static/img/placeholder/placeholder-1920-700.jpg', 'Sample Image Description');

INSERT INTO public.dcsc_carousel VALUES (1, 'Sample Caption', 1, 1);

INSERT INTO dcsc_tutoring.dcsc_tutors VALUES (1,1,TRUE);
INSERT INTO dcsc_tutoring.dcsc_tutors VALUES (2,2,TRUE);

INSERT INTO dcsc_tutoring.dcsc_courses VALUES (1, 'ECS10');
INSERT INTO dcsc_tutoring.dcsc_courses VALUES (2, 'ECS12');
INSERT INTO dcsc_tutoring.dcsc_courses VALUES (3, 'ECS15');
INSERT INTO dcsc_tutoring.dcsc_courses VALUES (4, 'ECS20');
INSERT INTO dcsc_tutoring.dcsc_courses VALUES (5, 'ECS30');

INSERT INTO dcsc_time.dcsc_academic_term VALUES (1, 'FQ', 2015, '2015-09-21', '2015-12-11');
INSERT INTO dcsc_time.dcsc_academic_term VALUES (2, 'WQ', 2016, '2016-01-04', '2016-03-19');
INSERT INTO dcsc_time.dcsc_academic_term VALUES (3, 'SQ', 2016, '2016-03-24', '2016-06-09');
INSERT INTO dcsc_time.dcsc_academic_term VALUES (4, 'SS1', 2016, '2016-06-20', '2016-07-29');
INSERT INTO dcsc_time.dcsc_academic_term VALUES (5, 'SS2', 2016, '2016-08-01', '2016-09-09');
INSERT INTO dcsc_time.dcsc_academic_term VALUES (6, 'FQ', 2016, '2016-09-19', '2015-12-09');
INSERT INTO dcsc_time.dcsc_academic_term VALUES (7, 'WQ', 2017, '2017-01-06', '2016-03-24');
INSERT INTO dcsc_time.dcsc_academic_term VALUES (8, 'SQ', 2017, '2017-03-30', '2016-06-15');

INSERT INTO dcsc_tutoring.dcsc_tutoring VALUES (1, 1, 1);
INSERT INTO dcsc_tutoring.dcsc_tutoring VALUES (1, 2, 1);
INSERT INTO dcsc_tutoring.dcsc_tutoring VALUES (2, 2, 1);
INSERT INTO dcsc_tutoring.dcsc_tutoring VALUES (2, 1, 2);
INSERT INTO dcsc_tutoring.dcsc_tutoring VALUES (2, 2, 2);
INSERT INTO dcsc_tutoring.dcsc_tutoring VALUES (2, 3, 2);

INSERT INTO dcsc_tutoring.dcsc_office_hours VALUES (1, '2016-01-16 02:30:00+00', '2016-01-16 04:30:00+00', 2, NULL);

INSERT INTO dcsc_tutoring.has_office_hours VALUES (2, 1);