package com.gbello.appgrade.models;

import java.util.ArrayList;

public class Subject {
    private String name;
    private ArrayList<Student> students;

    public Subject(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public float getAverageGrade() {
        int total = 0;
        for (Student student : students) {
            total += student.getAverageGrade(name);
        }
        return total / (float) students.size();
    }
}
