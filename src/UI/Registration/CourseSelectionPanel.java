package UI.Registration;

import Model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CourseSelectionPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public CourseSelectionPanel() {
        setLayout(new BorderLayout());


        model = new DefaultTableModel(
                new String[]{"Select", "ID", "Name"}, 0
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        table = new JTable(model);
        table.getColumnModel().getColumn(0).setMaxWidth(50);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }


    public void loadCourses(List<Course> courses) {
        model.setRowCount(0);
        for (Course c : courses) {
            model.addRow(new Object[]{false, c.getId(), c.getName()});
        }
    }

    // Return IDs of all selected (checked) courses
    public List<Integer> getSelectedCourseIds() {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean selected = (Boolean) model.getValueAt(i, 0);
            if (selected != null && selected) {
                ids.add((Integer) model.getValueAt(i, 1)); // course ID
            }
        }
        return ids;
    }


    public void clearSelection() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(false, i, 0);
        }
    }


    public JTable getTable() {
        return table;
    }
}

