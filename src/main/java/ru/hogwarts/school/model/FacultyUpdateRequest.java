package ru.hogwarts.school.model;

public class FacultyUpdateRequest {


    private long id;
    private String name;
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
