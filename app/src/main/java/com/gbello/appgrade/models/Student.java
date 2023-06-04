package com.gbello.appgrade.models;

import java.util.HashMap;
import java.util.TreeMap;

public class Student {
    private String ra;
    private String name;
    private HashMap<String, TreeMap<Integer, Integer>> grades;

    public String getRa() {
        return ra;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, TreeMap<Integer, Integer>> getGrades() {
        return grades;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrades(HashMap<String, TreeMap<Integer, Integer>> grades) {
        this.grades = grades;
    }

    public Student(String ra, String name) {
        this.ra = ra;
        this.name = name;
        this.grades = new HashMap<>();
    }

    public void addGrade(String subject, int grade, int term) {
        if (!grades.containsKey(subject)) {
            grades.put(subject, new TreeMap<>());
        }
        grades.get(subject).put(term, grade);
    }

    public float getAverageGrade(String subject) {
        TreeMap<Integer, Integer> subjectGrades = grades.get(subject);
        int total = 0;
        for (int grade : subjectGrades.values()) {
            total += grade;
        }
        return total / (float) subjectGrades.size();
    }

    @Override
    public String toString() {
        return name + " (" + ra + ")";
    }
}
