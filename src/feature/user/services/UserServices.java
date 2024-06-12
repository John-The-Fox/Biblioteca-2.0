package feature.user.services;

import feature.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServices {
    public static List<User> getUsersBySearch(String name,String username , String password, String DOB, String email, String phone , String address, List<User> fullList) {
        List<User> resultUsers = new ArrayList<>();
        for (User user : fullList) {
            if ((name.isEmpty() || name.equalsIgnoreCase(user.getName())) &&
                    (username.isEmpty() || username.equalsIgnoreCase(user.getUsername())) &&
                    (password.isEmpty() || password.equalsIgnoreCase(user.getPassword())) &&
                    (DOB.isEmpty() || DOB.equalsIgnoreCase(user.getDOB())) &&
                    (email.isEmpty() || email.equalsIgnoreCase(user.getEmail())) &&
                    (phone.isEmpty() || phone.equalsIgnoreCase(user.getPhone())) &&
                    (address.isEmpty() || address.equalsIgnoreCase(user.getAddress()))) {
                resultUsers.add(user);
            }
        }
        return resultUsers;
    }
}
