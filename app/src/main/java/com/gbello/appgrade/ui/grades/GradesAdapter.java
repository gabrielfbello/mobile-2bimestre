package com.gbello.appgrade.ui.grades;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gbello.appgrade.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GradesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_GRADE = 0;
    private static final int VIEW_TYPE_EMPTY = 1;

    private List<Map.Entry<String, Integer>> grades;

    public GradesAdapter(HashMap<String, TreeMap<Integer, Integer>> grades) {
        setGrades(grades);
    }

    public void setGrades(HashMap<String, TreeMap<Integer, Integer>> grades) {
        // Convert the TreeMap values to Integer
        Map<String, Integer> convertedGrades = new HashMap<>();
        for (Map.Entry<String, TreeMap<Integer, Integer>> entry : grades.entrySet()) {
            TreeMap<Integer, Integer> gradeMap = entry.getValue();
            Integer finalGrade = gradeMap.isEmpty() ? 0 : gradeMap.lastEntry().getValue();
            convertedGrades.put(entry.getKey(), finalGrade);
        }

        this.grades = new ArrayList<>(convertedGrades.entrySet());
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (grades.isEmpty()) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_GRADE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_GRADE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grade, parent, false);
            return new GradeViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty, parent, false);
            return new EmptyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_GRADE) {
            Map.Entry<String, Integer> entry = grades.get(position);
            String subject = entry.getKey();
            int grade = entry.getValue();
            ((GradeViewHolder) holder).bind(subject, grade);
        }
    }

    @Override
    public int getItemCount() {
        return grades.isEmpty() ? 1 : grades.size();
    }

    public static class GradeViewHolder extends RecyclerView.ViewHolder {
        private TextView subjectTextView;
        private TextView gradeTextView;

        public GradeViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            gradeTextView = itemView.findViewById(R.id.gradeTextView);
        }

        public void bind(String subject, int grade) {
            subjectTextView.setText(subject);
            gradeTextView.setText(String.valueOf(grade));
        }
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}