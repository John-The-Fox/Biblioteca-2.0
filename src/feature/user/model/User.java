package feature.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String name;
    private String password;
    private String DOB;
    private String email;
    private String phone;
    private String address;
    private boolean adm;

    public User() {}

    public User(int id, String name,String username , String password, String DOB, String email, String phone , String address, boolean adm) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.DOB = DOB;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.adm = adm;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getDOB() {
        return DOB;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public boolean isAdm() {
        return adm;
    }
    public int getId() {
        return id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setAdm(boolean adm) {
        this.adm = adm;
    }

}
