package UI.Course;

import Model.Course;
import Repository.CourseRepo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CourseTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public CourseTablePanel(){
        setLayout(new BorderLayout());
        model = new DefaultTableModel(
          new String[]{"ID", "Name"}, 0
        );
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
        loadCourses();
    }
    public void loadCourses(){
        model.setRowCount(0);
        for (Course c: CourseRepo.getCourses()){
            model.addRow(new Object[]{
                    c.getId(),
                    c.getName()
            });
        }
    }
    public int getSelectedCourseId(){
        int row = table.getSelectedRow();
        return row == -1? -1 : (int) model.getValueAt(row, 0);
    }
    public JTable getTable(){
        return table;
    }
}
