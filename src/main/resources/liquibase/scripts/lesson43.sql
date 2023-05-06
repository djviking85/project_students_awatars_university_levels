-- - liquibase formatted sql
-- - changeset student:1

CREATE index student_name_index on student (name);


-- -changeset chernenkov:2
CREATE INDEX faculty_color_index ON faculty(color, name);



