package feature.login.presentation;

public class LoginViewImpl implements LoginView {
    private final LoginController loginController;

    public LoginViewImpl(LoginController loginController) {
        this.loginController = loginController;
    }

    @Override
    public void show() {
        // Implementar a lógica da tela de login.
    }
}