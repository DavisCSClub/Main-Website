CREATE SCHEMA IF NOT EXISTS administration;
CREATE SCHEMA IF NOT EXISTS dcsc_accounts;
CREATE SCHEMA IF NOT EXISTS dcsc_tutoring;
CREATE SCHEMA IF NOT EXISTS dcsc_time;

CREATE TABLE IF NOT EXISTS dcsc_accounts.dcsc_roles (
  id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS dcsc_accounts.dcsc_class (
  id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  class_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS dcsc_accounts.dcsc_role_privileges (
  role_id       INT NOT NULL,
  permission_id INT NOT NULL,
  access_level  INT NOT NULL,
  CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES dcsc_accounts.dcsc_roles (id),
  CONSTRAINT permission_fk FOREIGN KEY (permission_id) REFERENCES dcsc_accounts.dcsc_class (id)
);

CREATE TABLE IF NOT EXISTS dcsc_accounts.dcsc_profiles (
  id          INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name        VARCHAR(255),
  email       VARCHAR(255),
  title       VARCHAR(255),
  description TEXT
);

CREATE TABLE IF NOT EXISTS dcsc_accounts.dcsc_users (
  id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  username   VARCHAR(255),
  password   VARCHAR(60),
  is_enabled BOOLEAN,
  is_locked  BOOLEAN,
  role_id    INT,
  profile_id INT,
  CONSTRAINT user_role_fk FOREIGN KEY (role_id) REFERENCES dcsc_accounts.dcsc_roles (id),
  CONSTRAINT user_profile_fk FOREIGN KEY (profile_id) REFERENCES dcsc_accounts.dcsc_profiles (id)
);

CREATE TABLE IF NOT EXISTS dcsc_accounts.dcsc_groups (
  id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS dcsc_accounts.dcsc_user_groups (
  user_id  INT     NOT NULL,
  group_id INT     NOT NULL,
  is_admin BOOLEAN NOT NULL,
  CONSTRAINT group_user_id_fk FOREIGN KEY (user_id) REFERENCES dcsc_accounts.dcsc_users (id),
  CONSTRAINT group_group_id_fk FOREIGN KEY (group_id) REFERENCES dcsc_accounts.dcsc_groups (id)
);

CREATE TABLE IF NOT EXISTS public.dcsc_images (
  id            INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name          TEXT,
  absolute_path TEXT,
  relative_path TEXT,
  description   TEXT
);

CREATE TABLE IF NOT EXISTS public.dcsc_events (
  id              INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name            VARCHAR(70),
  description     TEXT,
  tag             VARCHAR(30),
  date            DATE,
  starttime       TIME,
  endtime         TIME,
  image           VARCHAR(100),
  location        VARCHAR(50),
  published       BOOLEAN,
  thumbnail_id    INT,
  banner_id       INT,
  start_date_time TIMESTAMP,
  end_date_time   TIMESTAMP,
  CONSTRAINT thumbnail_fk FOREIGN KEY (thumbnail_id) REFERENCES public.dcsc_images (id),
  CONSTRAINT banner_fk FOREIGN KEY (banner_id) REFERENCES public.dcsc_images (id)
);

CREATE TABLE IF NOT EXISTS public.dcsc_carousel (
  id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  caption    VARCHAR(255),
  image_id   INT,
  precedence INT,
  CONSTRAINT image_fk FOREIGN KEY (image_id) REFERENCES public.dcsc_images (id)
);

CREATE TABLE IF NOT EXISTS dcsc_tutoring.dcsc_tutors (
  id        INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  common_id INT                            NOT NULL,
  is_active BOOLEAN,
  CONSTRAINT dcsc_tutor_user_profile_fk FOREIGN KEY (common_id) REFERENCES dcsc_accounts.dcsc_users (id),
);

CREATE TABLE IF NOT EXISTS dcsc_tutoring.dcsc_courses (
  id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  code VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS dcsc_time.dcsc_academic_term (
  id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  code       VARCHAR(3),
  year       INT,
  start_date DATE,
  end_date   DATE
);

CREATE TABLE IF NOT EXISTS dcsc_tutoring.dcsc_tutoring (
  tutor_id  INT,
  course_id INT,
  term_id   INT,
  PRIMARY KEY (tutor_id, course_id, term_id),
  CONSTRAINT dcsc_tutoring_tutor_fk FOREIGN KEY (tutor_id) REFERENCES dcsc_tutoring.dcsc_tutors (id),
  CONSTRAINT dcsc_tutoring_course_fk FOREIGN KEY (course_id) REFERENCES dcsc_tutoring.dcsc_courses (id),
  CONSTRAINT dcsc_tutoring_quarter_fk FOREIGN KEY (term_id) REFERENCES dcsc_time.dcsc_academic_term (id)
);

CREATE TABLE IF NOT EXISTS dcsc_tutoring.dcsc_office_hours (
  id              INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  start_timestamp TIMESTAMP                      NOT NULL,
  end_timestamp   TIMESTAMP                      NOT NULL,
  term_id         INT                            NOT NULL,
  child_id        INT,
  CONSTRAINT dcsc_oh_term_fk FOREIGN KEY (term_id) REFERENCES dcsc_time.dcsc_academic_term (id),
  CONSTRAINT dcsc_oh_child_fk FOREIGN KEY (child_id) REFERENCES dcsc_tutoring.dcsc_office_hours (id),
);

CREATE TABLE IF NOT EXISTS dcsc_tutoring.has_office_hours (
  tutor_id       INT,
  office_hour_id INT,
  PRIMARY KEY (tutor_id, office_hour_id),
  CONSTRAINT dcsc_oh_tutor_fk FOREIGN KEY (tutor_id) REFERENCES dcsc_tutoring.dcsc_tutors (id),
  CONSTRAINT dcsc_oh_office_hour_fk FOREIGN KEY (office_hour_id) REFERENCES dcsc_tutoring.dcsc_office_hours (id)
);

CREATE TABLE IF NOT EXISTS dcsc_tutoring.tutoring_sessions (
  id              INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  tutor_id        INT                            NOT NULL,
  start_timestamp TIMESTAMP                      NOT NULL,
  end_timestamp   TIMESTAMP,
  CONSTRAINT dcsc_tutor_session_tutor_fk FOREIGN KEY (tutor_id) REFERENCES dcsc_tutoring.dcsc_tutors (id)
);
