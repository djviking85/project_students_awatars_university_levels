- liquibase formatted sql
- changeset student:1

CREATE index student_name_index on Student (name);


-changeset chernenkov:2
CREATE INDEX faculty_color_index ON faculty(color, name);


- changeset chernenkov:3
--     удаляем индексы
DROP INDEX faculty_color_index;
drop index student_name_index;
