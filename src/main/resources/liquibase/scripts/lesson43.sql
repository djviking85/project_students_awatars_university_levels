-- - liquibase formatted sql
-- - changeset student:1

CREATE index if not exists student_name_index on student (name);


-- -changeset chernenkov:2
CREATE INDEX if not exists faculty_color_index ON faculty(color, name);



