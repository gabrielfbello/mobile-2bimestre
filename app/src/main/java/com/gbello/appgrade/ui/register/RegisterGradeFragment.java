package com.gbello.appgrade.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.gbello.appgrade.R;
import com.gbello.appgrade.databinding.FragmentRegisterBinding;
import com.gbello.appgrade.models.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RegisterGradeFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private RegisterGradeViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(RegisterGradeViewModel.class);
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Define os dados para o DropDown do assunto
        ArrayAdapter<CharSequence> subjectAdapter = ArrayAdapter.createFromResource(getContext(),
        R.array.subjects_array, android.R.layout.simple_spinner_item);
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.subjectSpinner.setAdapter(subjectAdapter);

        // Define os dados para o DropDown do termo
        ArrayAdapter<CharSequence> termAdapter = ArrayAdapter.createFromResource(getContext(),
        R.array.terms_array, android.R.layout.simple_spinner_item);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.termSpinner.setAdapter(termAdapter);

        binding.addButton.setOnClickListener(v -> {
            String ra = binding.raInput.getText().toString();
            String subject = binding.subjectSpinner.getSelectedItem() != null ? binding.subjectSpinner.getSelectedItem().toString() : "";
            String gradeString = binding.gradeInput.getText().toString();
            String termString = binding.termSpinner.getSelectedItem() != null ? binding.termSpinner.getSelectedItem().toString() : "";
            termString = termString.replaceAll("\\D+", "");

            if (!ra.isEmpty() && !subject.isEmpty() && !gradeString.isEmpty() && !termString.isEmpty()) {
                try {
                    int grade = Integer.parseInt(gradeString);
                    int term = Integer.parseInt(termString);

                    if (grade < 0 || grade > 100) {
                        Toast.makeText(getContext(), "Nota deve estar entre 0 e 100", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    viewModel.addGrade(ra, subject, grade, term);

                    Student student = viewModel.getStudentByRa(ra);
                    if (student != null) {
                        System.out.println("Student Name: " + student.getName());
                        System.out.println("Student RA: " + student.getRa());
                        HashMap<String, TreeMap<Integer, Integer>> grades = student.getGrades();
                        if (!grades.isEmpty()) {
                            System.out.println("Student Grades: ");
                            for (Map.Entry<String, TreeMap<Integer, Integer>> entry : grades.entrySet()) {
                                System.out.println("Subject: " + entry.getKey() + ", Grades: " + entry.getValue());
                            }
                        } else {
                            System.out.println("No grades available for this student.");
                        }
                    } else {
                        Toast.makeText(getContext(), "RA não encontrado", Toast.LENGTH_SHORT).show();
                    }

                    binding.raInput.setText("");
                    binding.gradeInput.setText("");

                    Toast.makeText(getContext(), "Nota adicionada com sucesso", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    Toast.makeText(getContext(), "RA e nota devem ser números", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Termo inválido", Toast.LENGTH_SHORT).show();
            }
        });


        binding.viewGradesButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_grades);
        });

        binding.viewAveragesButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_averages);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}