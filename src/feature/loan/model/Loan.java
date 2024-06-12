package feature.loan.model;

import feature.book.model.Book;
import feature.user.model.User;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
    private Date loanDate;
    private Date dueDate;
    private boolean returned;
    private String name;
    private String ra;
    private String email;
    private String phone;
    private String address;

    public Loan(){}

    public Loan(int id, User user, Book book, Date loanDate, Date dueDate, boolean returned, String name, String ra, String email, String phone, String address){
        this.id = id;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.name = name;
        this.ra = ra;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    //gets e sets
    public int getid () {
        return id;
    }
    /*public void setid (int id) {
        this.id = id;
    }*/
    public User getUser() {
        return user;
    }
    public void setUser (User user) {
        this.user = user;
    }
    public Book getBook() {
        return book;
    }
    public void setBook (Book book) {
        this.book = book;
    }
    public Date getLoanDate() {
        return loanDate;
    }
    public void setLoanDate (Date loanDate) {
        this.loanDate = loanDate;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate (Date dueDate) {
        this.dueDate = dueDate;
    }
    public boolean isReturned() {
        return returned;
    }
    public void setReturned(boolean returned) {
        this.returned = returned;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRa() {
        return ra;
    }
    public void setRa(String ra) {
        this.ra = ra;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}


