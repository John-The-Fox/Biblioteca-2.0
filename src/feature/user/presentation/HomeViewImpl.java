package feature.user.presentation;

import di.ServiceLocator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeViewImpl extends JFrame implements HomeView {
    private JButton usersButton;
    private JButton booksButton;
    private JButton exitButton;
    private LoginController loginController;

    public HomeViewImpl() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(null,
                        "Deseja realmente sair?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    // Fecha todas as janelas abertas
                    for (Window window : Window.getWindows()) {
                        window.dispose();
                    }
                    // Abre a tela de login
                    LoginView loginView = ServiceLocator.getInstance().getLoginView();
                    loginView.open();
                }
            }
        });

        initializeUI();
    }
    private void initializeUI() {
        setLayout(new GridLayout(1, 3));

        usersButton = new JButton("Usuarios");
        usersButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                ServiceLocator.getInstance().getUserView().open();
            });
        });
        add(usersButton);

        booksButton = new JButton("Livros");
        booksButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                ServiceLocator.getInstance().getBookView().open();
            });
        });
        add(booksButton);

        exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> {
            close();
        });
        add(exitButton);
    }
    @Override
    public void open(){
        setVisible(true);
    }
    @Override
    public void close(){
        setVisible(false);
        for (Window window : Window.getWindows()) {
            window.dispose();
        }
        SwingUtilities.invokeLater(() -> {
            ServiceLocator.getInstance().getLoginView().open();
        });
    }
    @Override
    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
