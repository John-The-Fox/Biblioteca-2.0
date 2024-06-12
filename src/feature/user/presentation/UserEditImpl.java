package feature.user.presentation;

import di.ServiceLocator;
import feature.user.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserEditImpl extends JFrame implements UserEdit {
    private final UserController userController;
    private final JTextField nameField;
    private final JTextField usernameField;
    private final JTextField passwordField;
    private final JTextField dobField;
    private final JTextField emailField;
    private final JTextField phoneField;
    private final JTextField addressField;
    private final JComboBox<String> roleComboBox; // Campo para exibir "Administrador" ou "Funcionário"
    private final JButton saveButton;
    private final JButton cancelButton;
    private int userId = -1;

    public UserEditImpl(UserController userController) {
        this.userController = userController;
        setTitle("Editar/Adicionar Usuário");
        setSize(400, 400);
        setLayout(new GridLayout(9, 2));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });

        // Criação dos campos
        add(new JLabel("Nome:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Nome de Usuário:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Senha:"));
        passwordField = new JTextField();
        add(passwordField);

        add(new JLabel("Data de Nascimento:"));
        dobField = new JTextField();
        add(dobField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Telefone:"));
        phoneField = new JTextField();
        add(phoneField);

        add(new JLabel("Endereço:"));
        addressField = new JTextField();
        add(addressField);

        add(new JLabel("Cargo:"));
        roleComboBox = new JComboBox<>(new String[]{"Administrador", "Funcionário"});
        add(roleComboBox);

        // Botões
        saveButton = new JButton("Salvar");
        saveButton.addActionListener(e -> saveUser(userId));
        add(saveButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> close());
        add(cancelButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void open() {
        clearFields();
        setVisible(true);
        setTitle("Adicionar Usuário");
    }

    @Override
    public void open(User user) {
        setUserDetails(user);
        userId = user.getId();
        setTitle("Editar Usuário ID: " + user.getId());
        setVisible(true);
    }

    @Override
    public void close() {
        setVisible(false);
        dispose();
    }

    @Override
    public void setUserDetails(User user) {
        nameField.setText(user.getName());
        usernameField.setText(user.getUsername());
        passwordField.setText(user.getPassword());
        dobField.setText(user.getDOB());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        addressField.setText(user.getAddress());
        roleComboBox.setSelectedItem(user.isAdm() ? "Administrador" : "Funcionário");
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void saveUser(int userId) {
        try {
            String name = nameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String dob = dobField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            boolean isAdmin = "Administrador".equals(roleComboBox.getSelectedItem());

            if (userId == -1) {
                userController.addUser(name, username, password, dob, email, phone, address, isAdmin);
            } else {
                userController.editUser(userId, name, username, password, dob, email, phone, address, isAdmin);
            }
            userController.refreshUsers();
            JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");
            close();
        } catch (Exception e) {
            showErrorMessage("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    private void clearFields() {
        nameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        dobField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
        roleComboBox.setSelectedItem("Funcionário");
    }
}
