package feature.user.presentation;

import di.ServiceLocator;
import feature.user.datasource.UserDAO;
import feature.user.model.User;
import global.Globals;

import javax.swing.*;

public class LoginControllerImpl implements LoginController {
    private final UserDAO userDAO;
    private final LoginView loginView;

    public LoginControllerImpl(UserDAO userDAO, LoginView loginView) {
        this.userDAO = userDAO;
        this.loginView = loginView;
    }

    @Override
    public void verifyLogin(String username, String password) {
        User user = userDAO.verifyCredentials(username, password);
        if (user != null) {
            Globals.setAdmin(user.isAdm());
            Globals.setCurrentUserId(user.getId());
            if (user.isAdm()) {
                // Redirecionar para a tela "Home"
                JOptionPane.showMessageDialog(null, "Bem-vindo, Administrador!");
                SwingUtilities.invokeLater(() -> {
                    ServiceLocator.getInstance().getHomeView().open();
                });
            } else {
                // Redirecionar para a tela de livros (BookView)
                JOptionPane.showMessageDialog(null, "Bem-vindo, Usuário!");
                SwingUtilities.invokeLater(() -> {
                    ServiceLocator.getInstance().getBookView().open();
                });
            }
            loginView.close();
        } else {
            loginView.showErrorMessage("Usuário ou senha inválidos.");
        }
    }
}
