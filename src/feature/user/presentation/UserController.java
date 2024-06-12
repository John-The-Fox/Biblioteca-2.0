package feature.user.presentation;

import feature.user.model.User;


import java.util.List;

public interface UserController {
    void setView(UserView view);

    void addUser(String name,String username , String password, String DOB, String email, String phone , String address, boolean adm);

    List<User> getUsers();

    void deleteUser(int id);

    void editUser(int id, String name, String username , String password, String DOB, String email, String phone , String address, boolean adm);

    void refreshUsers();

    User getUserById(int id);
}
