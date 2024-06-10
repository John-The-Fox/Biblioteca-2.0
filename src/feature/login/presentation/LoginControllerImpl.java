package feature.login.presentation;

import feature.user.datasource.UserDAO;

public class LoginControllerImpl implements LoginController {
    private final UserDAO userDAO;

    public LoginControllerImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean authenticate(String username, String password) {
        // Implementar a lógica de autenticação.
        return userDAO.validateCredentials(username, password);
    }
}