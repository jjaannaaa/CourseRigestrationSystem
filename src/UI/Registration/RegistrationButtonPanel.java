package UI.Registration;

import javax.swing.*;

public class RegistrationButtonPanel extends JPanel {
    private JButton btnRegister;
    private JButton btnUnregister;
    private JButton btnClear;

    public RegistrationButtonPanel() {

        btnRegister = new JButton("Register");
        btnUnregister = new JButton("Unregister");
        btnClear = new JButton("Clear");


        add(btnRegister);
        add(btnUnregister);
        add(btnClear);
    }

    public JButton getRegisterButton() {
        return btnRegister;
    }

    public JButton getUnregisterButton() {
        return btnUnregister;
    }

    public JButton getBtnClear() {
        return btnClear;
    }
}
