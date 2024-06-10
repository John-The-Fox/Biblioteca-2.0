package feature.login.presentation;

public interface LoginController {
    boolean authenticate(String username, String password);
}