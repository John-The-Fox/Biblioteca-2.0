package feature.user.presentation;

import feature.user.model.User;

public interface   UserEdit{
    void open();
    void open(User user);
    void close();
    void setUserDetails(User user);
    void showErrorMessage(String message);
}
