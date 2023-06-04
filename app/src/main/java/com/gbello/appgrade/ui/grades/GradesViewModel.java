package com.gbello.appgrade.ui.grades;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gbello.appgrade.models.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GradesViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Student>> mStudents;
    private final GradesRepository gradesRepository;

    public GradesViewModel() {
        gradesRepository = new GradesRepository();
        mStudents = gradesRepository.getStudents();
        sortStudentsAlphabetically(mStudents.getValue());
    }

    public LiveData<ArrayList<Student>> getStudents() {
        return mStudents;
    }

    private void sortStudentsAlphabetically(ArrayList<Student> students) {
        if (students != null) {
            Collections.sort(students, new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return s1.getName().compareTo(s2.getName());
                }
            });
        }
    }
}
