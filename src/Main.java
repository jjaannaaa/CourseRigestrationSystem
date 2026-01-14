import UI.MyFrame;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new MyFrame().setVisible(true)
        );
    }
}
// accidentally deleted the project, so I tried to clone it and now commit it again