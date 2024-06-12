package feature.user.presentation;

import feature.user.datasource.UserListener;
import feature.user.datasource.UserSubscriber;
import feature.user.model.User;
import feature.user.services.UserServices;
import di.ServiceLocator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class UserViewImpl extends JFrame implements UserView, UserListener {
    private final DefaultTableModel tableModel;
    private final UserController userController;

    public UserViewImpl(UserSubscriber userSubscriber,UserController userController) {
        setTitle("Usuarios");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });

        this.userController = userController;
        userController.setView(this);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Username", "Password", "D.de nasc.", "E-mail", "Fone", "End."}, 0);
        initializeUI();

        // Carregar livros do banco de dados
        loadUsers();
        userSubscriber.subscribe(this);
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JTable userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("ID:"));
        JTextField idField = new JTextField(20);
        formPanel.add(idField);

        formPanel.add(new JLabel("Nome:"));
        JTextField nameField = new JTextField(20);
        formPanel.add(nameField);

        formPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField(20);
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        JTextField passwordField = new JTextField(20);
        formPanel.add(passwordField);

        formPanel.add(new JLabel("D. de nasc.:"));
        JTextField DOBField = new JTextField(20);
        formPanel.add(DOBField);

        formPanel.add(new JLabel("E-mail:"));
        JTextField emailField = new JTextField(20);
        formPanel.add(emailField);

        formPanel.add(new JLabel("fone:"));
        JTextField phoneField = new JTextField(20);
        formPanel.add(phoneField);

        formPanel.add(new JLabel("end.:"));
        JTextField addressField = new JTextField(20);
        formPanel.add(addressField);


        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton searchButton = new JButton("Pesquisar");
        searchButton.addActionListener(e -> {

            String name = nameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String DOB = DOBField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            List<User> fullList = userController.getUsers();
            List<User> users = UserServices.getUsersBySearch(name,username , password, DOB, email, phone , address, fullList);
            updateUserTable(users);
        });
        buttonPanel.add(searchButton);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                ServiceLocator.getInstance().getUserEdit().open();
            });
        });
        buttonPanel.add(addButton);// somente se usuario for adm

        JButton editButton = new JButton("Editar");
        editButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                int selectedId = (int) tableModel.getValueAt(selectedRow, 0);
                User user = userController.getUserById(selectedId);
                SwingUtilities.invokeLater(() -> {
                    ServiceLocator.getInstance().getUserEdit().open(user);
                });
            } else {
                showErrorMessage("Selecione um livro para editar.");
            }
        });
        buttonPanel.add(editButton);// somente se usuario for adm

        JButton deleteButton = new JButton("Excluir");
        deleteButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                int selectedId = (int) tableModel.getValueAt(selectedRow, 0);
                userController.deleteUser(selectedId);
                updateData();
            } else {
                showErrorMessage("Selecione um livro para excluir.");
            }
        });
        buttonPanel.add(deleteButton);// somente se usuario for adm
        JButton refreshButton = new JButton("Recarregar");
        refreshButton.addActionListener(e -> {
            updateData();
        });
        buttonPanel.add(refreshButton);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadUsers() {
        List<User> users = userController.getUsers();
        updateUserTable(users);
    }

    private void updateUserTable(List<User> users) {
        tableModel.setRowCount(0);
        for (User user : users) {
            tableModel.addRow(new Object[]{user.getId(), user.getName(), user.getUsername(), user.getPassword(),user.getDOB(), user.getEmail(), user.getPhone(), user.getAddress()});
        }
    }

    @Override
    public void open() {
        setVisible(true);
    }

    @Override
    public void close() {
        dispose();
    }

    @Override
    public void minimize() {
        setVisible(false);
    }

    @Override
    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void updateData() {
        userController.refreshUsers();
        loadUsers();
    }
}
