INSERT INTO dcsc_accounts.dcsc_roles VALUES (1, 'SUPER');
INSERT INTO dcsc_accounts.dcsc_roles VALUES (2, 'BASIC');

INSERT INTO dcsc_accounts.dcsc_class VALUES (1, 'CAROUSEL');
INSERT INTO dcsc_accounts.dcsc_class VALUES (2, 'EVENT');
INSERT INTO dcsc_accounts.dcsc_class VALUES (3, 'USER');

INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (1, 1, 15);
INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (1, 2, 15);
INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (1, 3, 15);

INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (2, 1, 1);
INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (2, 2, 15);
INSERT INTO dcsc_accounts.dcsc_role_privileges VALUES (2, 3, 1);

INSERT INTO dcsc_accounts.dcsc_profiles VALUES (1, 'Root', 'johnDoe@nowhere.org', 'President', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (2, 'John Doe', 'johnDoe@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (3, 'Jane Doe', 'janeDoe@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (4, 'User 4', 'user4@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (5, 'User 5', 'user5@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (6, 'User 6', 'user6@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (7, 'User 7', 'user7@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (8, 'User 8', 'user8@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (9, 'User 9', 'user9@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (10, 'User 10', 'user10@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (11, 'User 11', 'user11@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (12, 'User 12', 'user12@nowhere.org', 'Title', 'Sample Profile');
INSERT INTO dcsc_accounts.dcsc_profiles VALUES (13, 'User 13', 'user13@nowhere.org', 'Title', 'Sample Profile');

-- local development password for these users is "password"
INSERT INTO dcsc_accounts.dcsc_users VALUES (1, 'dcsc', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,1,1 );
INSERT INTO dcsc_accounts.dcsc_users VALUES (2, 'john.doe', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,2);
INSERT INTO dcsc_accounts.dcsc_users VALUES (3, 'jane.doe', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,3);
INSERT INTO dcsc_accounts.dcsc_users VALUES (4, 'user4', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,4);
INSERT INTO dcsc_accounts.dcsc_users VALUES (5, 'user5', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,5);
INSERT INTO dcsc_accounts.dcsc_users VALUES (6, 'user6', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,6);
INSERT INTO dcsc_accounts.dcsc_users VALUES (7, 'user7', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,7);
INSERT INTO dcsc_accounts.dcsc_users VALUES (8, 'user8', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,8);
INSERT INTO dcsc_accounts.dcsc_users VALUES (9, 'user9', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,9);
INSERT INTO dcsc_accounts.dcsc_users VALUES (10, 'user10', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,10);
INSERT INTO dcsc_accounts.dcsc_users VALUES (11, 'user11', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,11);
INSERT INTO dcsc_accounts.dcsc_users VALUES (12, 'user12', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,12);
INSERT INTO dcsc_accounts.dcsc_users VALUES (13, 'user13', '$2a$10$LLExsv.PneZ4I.iT6C3ZyO45g4xRYLDZmtIkaYj.qRkd2bwrCTnDC', TRUE,FALSE,2,13);

INSERT INTO dcsc_accounts.dcsc_groups VALUES (1, 'My Group');
INSERT INTO dcsc_accounts.dcsc_groups VALUES (2, 'Tutoring Committee');

INSERT INTO dcsc_accounts.dcsc_user_groups VALUES (1,1, TRUE);
INSERT INTO dcsc_accounts.dcsc_user_groups VALUES (1,2, TRUE);
INSERT INTO dcsc_accounts.dcsc_user_groups VALUES (2,2, FALSE);

INSERT INTO public.dcsc_events VALUES (1, 'Sample Event 1', 'Sample Description', 'tag', '2016-01-02', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (2, 'Sample Event 2', 'Sample Description', 'tag', '2016-01-03', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (3, 'Sample Event 3', 'Sample Description', 'tag', '2016-01-04', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (4, 'Sample Event 4', 'Sample Description', 'tag', '2016-01-05', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (5, 'Sample Event 5', 'Sample Description', 'tag', '2016-01-06', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (6, 'Sample Event 6', 'Sample Description', 'tag', '2016-01-07', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (7, 'Sample Event 7', 'Sample Description', 'tag', '2016-01-08', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (8, 'Sample Event 8', 'Sample Description', 'tag', '2016-01-09', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (9, 'Sample Event 9', 'Sample Description', 'tag', '2016-01-10', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (10, 'Sample Event 10', 'Sample Description', 'tag', '2016-01-11', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (11, 'Sample Event 11', 'Sample Description', 'tag', '2016-01-12', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (12, 'Sample Event 12', 'Sample Description', 'tag', '2016-01-13', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (13, 'Sample Event 13', 'Sample Description', 'tag', '2016-01-14', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (14, 'Sample Event 14', 'Sample Description', 'tag', '2016-01-15', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (15, 'Sample Event 15', 'Sample Description', 'tag', '2016-01-16', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (16, 'Sample Event 16', 'Sample Description', 'tag', '2016-01-17', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (17, 'Sample Event 17', 'Sample Description', 'tag', '2016-01-18', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (18, 'Sample Event 18', 'Sample Description', 'tag', '2016-01-19', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (19, 'Sample Event 19', 'Sample Description', 'tag', '2016-01-20', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);
INSERT INTO public.dcsc_events VALUES (20, 'Sample Event 20', 'Sample Description', 'tag', '2016-01-21', '11:30:00', '15:46:00', NULL, 'somewhere',TRUE, NULL, NULL, NULL,NULL);

INSERT INTO public.dcsc_images VALUES (1, 'Sample Image', NULL, '/static/img/placeholder/placeholder-1920-700.jpg', 'Sample Image Description');

INSERT INTO public.dcsc_carousel VALUES (1, 'Sample Caption', 1, 1);
INSERT INTO public.dcsc_carousel VALUES (2, 'Sample Caption', 1, 2);

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