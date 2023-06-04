    package com.gbello.appgrade.ui.register;

    import androidx.lifecycle.LiveData;
    import androidx.lifecycle.MutableLiveData;
    import androidx.lifecycle.ViewModel;
    import androidx.lifecycle.ViewModelProvider;
    import androidx.lifecycle.ViewModelStoreOwner;

    import com.gbello.appgrade.models.Student;
    import com.gbello.appgrade.ui.SharedViewModel;
    import com.gbello.appgrade.ui.grades.GradesViewModel;

    import java.util.ArrayList;
    import java.util.List;

    public class RegisterGradeViewModel extends ViewModel {

        private List<Student> students;

        public RegisterGradeViewModel() {
            students = createHardcodedStudents();
        }

        private List<Student> createHardcodedStudents() {
            List<Student> students = new ArrayList<>();
            students.add(new Student("00212345", "Vladimir Lenin"));
            students.add(new Student("00223456", "Joseph Stalin"));
            students.add(new Student("00234567", "Nikita Khrushchev"));
            students.add(new Student("00209970", "Garry Kasparov"));
            students.add(new Student("00256789", "Mikhail Gorbachev"));
            students.add(new Student("00267890", "Vladimir Putin"));
            students.add(new Student("00278901", "Boris Yeltsin"));
            students.add(new Student("00289012", "Yuri Gagarin"));
            students.add(new Student("00290123", "Alexei Kosygin"));
            students.add(new Student("00201234", "Andrei Gromyko"));
            students.add(new Student("00256789", "Georgy Zhukov"));
            students.add(new Student("00267890", "Aleksandr Solzhenitsyn"));
            students.add(new Student("00278901", "Anastas Mikoyan"));
            students.add(new Student("00289012", "Konstantin Chernenko"));
            students.add(new Student("00290123", "Dmitry Medvedev"));
            students.add(new Student("00201234", "Yuri Andropov"));
            students.add(new Student("00256789", "Nikolai Bulganin"));
            students.add(new Student("00267890", "Anatoly Dobrynin"));
            students.add(new Student("00278901", "Vyacheslav Molotov"));
            students.add(new Student("00289012", "Sergei Korolev"));

            return students;
        }

        public List<Student> getStudents() {
            return students;
        }

        public Student getStudentByRa(String ra) {
            for (Student student : students) {
                if (student.getRa().equals(ra)) {
                    return student;
                }
            }
            return null;
        }

        public void addGrade(String ra, String subject, int grade, int term) {
            for (Student student : students) {
                if (student.getRa().equals(ra)) {
                    student.addGrade(subject, grade, term);
                    SharedViewModel sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(SharedViewModel.class);
                    sharedViewModel.setIsGradeUpdated(true);
                    return;
                }
            }
        }

    }