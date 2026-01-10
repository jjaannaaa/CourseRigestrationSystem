import DataBase.DataBaseConnection;
import DataBase.OracleConnection;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
//        //panel
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(0xFFF3D0));
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//        //frame
//        UI.MyFrame Register = new UI.MyFrame();
//        Register.add(panel);
//        Register.setVisible(true);


        //connection test
        DataBaseConnection oracle = new OracleConnection();
        Connection connection = oracle.getConnection();
        oracle.closeConnection(connection);
    }
}