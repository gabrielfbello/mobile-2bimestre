package com.gbello.appgrade.ui.average;

import androidx.lifecycle.ViewModel;

import com.gbello.appgrade.models.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AverageViewModel extends ViewModel {

    public float calculateAverage(Student student) {
        HashMap<String, TreeMap<Integer, Integer>> grades = student.getGrades();
        int totalGrades = 0;
        int totalSubjects = 0;
        for (TreeMap<Integer, Integer> subjectGrades : grades.values()) {
            for (Map.Entry<Integer, Integer> entry : subjectGrades.entrySet()) {
                totalGrades += entry.getValue();
            }
            totalSubjects += subjectGrades.size();
        }
        return totalSubjects > 0 ? (float) totalGrades / totalSubjects : 0;
    }
}