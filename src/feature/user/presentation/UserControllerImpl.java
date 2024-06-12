package feature.user.presentation;

import feature.user.datasource.UserDatabase;
import feature.user.model.User;
import feature.user.presentation.UserView;

import java.util.ArrayList;
import java.util.List;

public class UserControllerImpl implements UserController {
    private UserView userView;
    private UserDatabase userDatabase;
    private List<User> users = new ArrayList<>();

    public UserControllerImpl(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public void setView(UserView view){
        this.userView = view;
    }

    @Override
    public void addUser(String name,String username , String password, String DOB, String email, String phone , String address, boolean adm){
        userDatabase.insertUser(name,username , password, DOB, email, phone , address, adm);
    }

    @Override
    public List<User> getUsers(){
        if (users.isEmpty()){
            users = userDatabase.getUsers();
        }
        return users;
    }

    @Override
    public void deleteUser(int id){
        userDatabase.deleteUser(id);
    }

    @Override
    public void editUser(int id, String name, String username , String password, String DOB, String email, String phone , String address, boolean adm){
        userDatabase.editUser( id, name, username , password, DOB, email, phone , address, adm);
    }

    @Override
    public void refreshUsers(){
        users = userDatabase.getUsers();
    }

    @Override
    public User getUserById(int id){
        List<User> users = userDatabase.getUsers();
        for (User user : users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }
}
