package UI;
import UI.Course.CoursePanel;
import UI.Student.StudentPanel;
import javax.swing.*;


public class MyFrame extends JFrame {
    public MyFrame(){
        setTitle("Student Registration System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Students", new StudentPanel());
        tabs.add("Courses", new CoursePanel());
        add(tabs);
    }
}
