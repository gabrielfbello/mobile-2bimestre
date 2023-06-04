package com.gbello.appgrade.ui.grades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gbello.appgrade.databinding.FragmentGradesBinding;
import com.gbello.appgrade.models.Student;
import com.gbello.appgrade.ui.SharedViewModel;

public class GradesFragment extends Fragment {

    public FragmentGradesBinding binding;
    private GradesViewModel gradesViewModel;
    private SharedViewModel sharedViewModel;
    private ArrayAdapter<Student> studentAdapter;
    private GradesAdapter gradesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGradesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        gradesViewModel = new ViewModelProvider(this).get(GradesViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getIsGradeUpdated().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isGradeUpdated) {
                if (isGradeUpdated) {
                    updateGrades();
                    sharedViewModel.setIsGradeUpdated(false);
                }
            }
        });


        return root;
    }

    private void updateGrades() {
        Student selectedStudent = (Student) binding.studentSpinner.getSelectedItem();
        if (selectedStudent != null) {
            gradesAdapter.setGrades(selectedStudent.getGrades());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
