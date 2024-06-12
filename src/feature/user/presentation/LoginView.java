package feature.user.presentation;

public interface LoginView {
    void open();
    void close();
    void showErrorMessage(String message);
    void setLoginController(LoginController controller);
}
