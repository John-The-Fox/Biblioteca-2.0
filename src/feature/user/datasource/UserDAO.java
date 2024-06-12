package feature.user.datasource;

import feature.book.datasource.BookListener;
import feature.user.model.User;
import infrastructure.DatabaseManager;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDatabase,UserSubscriber{
    private final List<UserListener> listeners;

    public UserDAO() {
        this(new ArrayList<>());
    }

    public UserDAO(List<UserListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void subscribe(UserListener userListener) {
        listeners.add(userListener);
    }

    private void notifyDataChanged() {
        for (UserListener listener : listeners) {
            listener.updateData();
        }
    }

    @Override
    public void insertUser(String name,String username , String password, String DOB, String email, String phone , String address, boolean adm) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var user = new User(name, username, password, DOB, email, phone , address, adm);
                session.persist(user);
            });
            System.out.println("feature.user.model.User inserted successfully.");
            notifyDataChanged();
        } catch (Exception e) {
            System.out.println("feature.user.model.User insertion failed: " + e.getMessage());
        }
    }

    public void editUser(int id, String name,String username , String password, String DOB, String email, String phone , String address, boolean adm) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var user = session.get(User.class, id);
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password);
                user.setDOB(DOB);
                user.setEmail(email);
                user.setPhone(phone);
                user.setAddress(address);
                user.setAdm(adm);
            });
            System.out.println("feature.user.model.User updated successfully.");
            notifyDataChanged();
        } catch (Exception e) {
            System.out.println("feature.user.model.User update failed: " + e.getMessage());
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createQuery("from User").list();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteUser(int id) {
        try{
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var user = session.get(User.class, id);
                if (user != null) {
                    session.remove(user);
                    System.out.println("User deleted successfully.");
                } else {
                    System.out.println("User with id " + id + " not found.");
                }
            });
            notifyDataChanged();
        } catch (Exception e) {
            System.out.println("User deletion failed: " + e.getMessage());
        }
    }

    public boolean validateCredentials(String username, String password) {
        // Implementar validação de credenciais de usuário.
        return false;
    }


}