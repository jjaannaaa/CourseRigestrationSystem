import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xFFF3D0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //frame
        MyFrame Register = new MyFrame();
        Register.add(panel);
        Register.setVisible(true);
    }
}