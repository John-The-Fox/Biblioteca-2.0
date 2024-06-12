package feature.user.datasource;

import feature.user.model.User;

import java.util.List;

public interface UserDatabase {
    void insertUser(String name,String username , String password, String DOB, String email, String phone , String address, boolean adm);

    void editUser(int id, String name, String username , String password, String DOB, String email, String phone , String address, boolean adm);

    List<User> getUsers();

    void deleteUser(int id);
}
