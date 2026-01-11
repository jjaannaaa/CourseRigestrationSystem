package UI.Course;

import Repository.CourseRepo;

import javax.swing.*;
import java.awt.*;

public class CoursePanel extends JPanel {
    private CourseFormPanel form;
    private CourseTablePanel table;
    private CourseButtonPanel buttons;

    public CoursePanel(){
        setLayout(new BorderLayout());

        form = new CourseFormPanel();
        table = new CourseTablePanel();
        buttons = new CourseButtonPanel();

        add(form, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        wireActions();
    }
    private void wireActions(){
        buttons.getBtnClear().addActionListener(e -> form.clearFields());
        buttons.getAddButton().addActionListener(e -> {
            String name = form.getNameText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Course name cannot be empty",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            CourseRepo.addCourse(name);
            table.loadCourses();
            form.clearFields();
        });
        buttons.getDeleteButton().addActionListener(e -> {
            int courseId = table.getSelectedCourseId();

            if (courseId == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a course to delete",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this course?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) return;

            CourseRepo.deleteCourse(courseId);
            table.loadCourses();
            form.clearFields();
        });
        buttons.getUpdateButton().addActionListener(e -> {
            int courseId = table.getSelectedCourseId();
            String name = form.getNameText().trim();

            if (courseId == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a course to update",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Course name cannot be empty",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            CourseRepo.updateCourse(courseId, name);
            table.loadCourses();
            form.clearFields();
        });
        table.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getTable().getSelectedRow();
                if (row != -1) {
                    String name = (String) table.getTable().getValueAt(row, 1);
                    form.setNameText(name);
                }
            }
        });
    }
}
