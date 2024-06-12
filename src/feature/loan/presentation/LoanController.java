package feature.loan.presentation;

import feature.book.model.Book;
import feature.book.presentation.BookView;
import feature.loan.model.Loan;
import feature.user.model.User;

import java.util.Date;
import java.util.List;

public interface LoanController {
    void rentBook(Book book, User user, Date loanDate, Date dueDate, boolean returned, String name, String ra, String email, String phone, String address);
    void setView(LoanView view);
    List<Loan> getActiveLoans();
    void returnLoan(int loanId);
    void refreshLoans();
}