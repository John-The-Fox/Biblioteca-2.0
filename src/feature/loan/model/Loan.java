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

    public Loan(){}

    public Loan(int id, User user, Book book, Date loanDate, Date dueDate, boolean returned){
        this.id = id;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }
    //gets e sets
    public int getid () {
        return id;
    }
    public void setid (int id) {
        this.id = id;
    }
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
}


