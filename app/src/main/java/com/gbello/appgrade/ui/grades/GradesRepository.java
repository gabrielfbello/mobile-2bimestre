package com.gbello.appgrade.ui.grades;

import androidx.lifecycle.MutableLiveData;

import com.gbello.appgrade.models.Student;
import com.gbello.appgrade.ui.register.RegisterGradeViewModel;

import java.util.ArrayList;
import java.util.List;

public class GradesRepository {

    private RegisterGradeViewModel registerGradeViewModel;

    public GradesRepository() {
        registerGradeViewModel = new RegisterGradeViewModel();
    }

    public MutableLiveData<ArrayList<Student>> getStudents() {
        MutableLiveData<ArrayList<Student>> students = new MutableLiveData<>();

        // Fetch students from the hardcoded list in RegisterGradeViewModel
        List<Student> fetchedStudents = (List<Student>) registerGradeViewModel.getStudents();
        ArrayList<Student> studentArrayList = new ArrayList<>(fetchedStudents);

        students.setValue(studentArrayList);
        return students;
    }
}
