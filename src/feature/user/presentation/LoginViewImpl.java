package feature.user.presentation;

import javax.swing.*;
import java.awt.*;

public class LoginViewImpl extends JFrame implements LoginView {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    private LoginController loginController;

    public LoginViewImpl() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridLayout(3, 2));

        // Usuário
        add(new JLabel("Usuário:"));
        usernameField = new JTextField();
        add(usernameField);

        // Senha
        add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        add(passwordField);

        // Botão de Login
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            if (loginController != null) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                loginController.verifyLogin(username, password);
            }
        });
        add(loginButton);

        //sair
        exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> {
            close();
        });
        add(exitButton);
    }

    @Override
    public void open() {
        setVisible(true);
    }

    @Override
    public void close() {
        setVisible(false);
        dispose();
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void setLoginController(LoginController controller) {
        this.loginController = controller;
    }
}
