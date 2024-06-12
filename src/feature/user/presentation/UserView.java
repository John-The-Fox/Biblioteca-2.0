package feature.user.presentation;

public interface UserView {
    void open();
    void close();
    void minimize();
    void showErrorMessage(String msg);
}
