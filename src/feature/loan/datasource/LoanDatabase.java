package feature.loan.datasource;

import feature.book.model.Book;
import feature.user.model.User;
import feature.loan.model.Loan;

import java.util.Date;
import java.util.List;

public interface LoanDatabase {

    void rentBook(Book book, User user, Date loanDate, Date dueDate, boolean returned, String name, String ra, String email, String phone, String address);

    void returnBook(int id);

    List<Loan> getActiveLoans();
}
