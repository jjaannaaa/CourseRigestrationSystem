package UI.Registration;

import Model.Course;
import Repository.CourseRepo;
import Repository.RegistrationRepo;
import UI.Student.StudentTablePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegistrationPanel extends JPanel {
    private StudentTablePanel studentTable;
    private CourseSelectionPanel courseTable;
    private RegistrationButtonPanel buttons;
    public RegistrationPanel() {
        setLayout(new BorderLayout(10, 10));

        // Panels
        studentTable = new StudentTablePanel();
        courseTable = new CourseSelectionPanel();
        buttons = new RegistrationButtonPanel();

        // JSplitPane: Left = Students, Right = Courses
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(studentTable),
                new JScrollPane(courseTable)
        );
        splitPane.setDividerLocation(250); // left panel width
        splitPane.setResizeWeight(0.3);    // left takes 30% of space

        add(splitPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        wireActions();
    }

    private void wireActions() {
        // When selecting a student → load enrolled courses
        studentTable.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int studentId = studentTable.getSelectedStudentId();
                if (studentId != -1) {
                    // Load all courses
                    List<Course> allCourses = CourseRepo.getCourses();
                    courseTable.loadCourses(allCourses);

                    // Check courses already registered for this student
                    List<Course> enrolled = RegistrationRepo.getCoursesForStudent(studentId);
                    for (int i = 0; i < courseTable.getTable().getRowCount(); i++) {
                        int courseId = (int) courseTable.getTable().getValueAt(i, 1);
                        for (Course c : enrolled) {
                            if (c.getId() == courseId) {
                                courseTable.getTable().setValueAt(true, i, 0);
                            }
                        }
                    }
                }
            }
        });

        // Clear checkbox selection
        buttons.getBtnClear().addActionListener(e -> courseTable.clearSelection());

        // Register selected courses
        buttons.getRegisterButton().addActionListener(e -> {
            int studentId = studentTable.getSelectedStudentId();
            if (studentId == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<Integer> selectedCourses = courseTable.getSelectedCourseIds();
            for (int courseId : selectedCourses) {
                RegistrationRepo.registerStudentToCourse(studentId, courseId);
            }
            // Refresh UI
            studentTable.loadStudents();
            courseTable.clearSelection();
        });

        // Unregister selected courses
        buttons.getUnregisterButton().addActionListener(e -> {
            int studentId = studentTable.getSelectedStudentId();
            if (studentId == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<Integer> selectedCourses = courseTable.getSelectedCourseIds();
            for (int courseId : selectedCourses) {
                RegistrationRepo.deleteRegistration(studentId, courseId);
            }
            // Refresh UI
            studentTable.loadStudents();
            courseTable.clearSelection();
        });
    }
}
