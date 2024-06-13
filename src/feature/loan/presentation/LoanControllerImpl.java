package feature.loan.presentation;

import feature.book.datasource.BookDatabase;
import feature.book.model.Book;
import feature.loan.presentation.LoanView;
import feature.loan.model.Loan;
import feature.loan.datasource.LoanDAO;
import feature.loan.datasource.LoanDatabase;
import feature.user.model.User;

import java.util.Date;
import java.util.List;

public class LoanControllerImpl implements LoanController {
    private LoanView loanView;
    private LoanDAO loanDAO;
    private LoanDatabase loanDatabase;
    private BookDatabase bookDatabase;
    private List<Loan> loans;

    public LoanControllerImpl(LoanDatabase loanDatabase , BookDatabase bookDatabase) {
        this.loanDatabase = loanDatabase;
        this.bookDatabase = bookDatabase;
    }

    @Override
    public void setView(LoanView view){
        this.loanView = view;
    }

    @Override
    public void rentBook(Book book, User user, Date loanDate, Date dueDate, boolean returned, String name, String ra, String email, String phone, String address) {
        bookDatabase.updateBook(book.getId(),book.getName(),book.getAuthor(),book.getYear(),book.getGenre(),book.getISBN(),book.getCopies()-1);
        loanDatabase.rentBook(book, user, loanDate, dueDate, returned, name, ra, email, phone, address);
    }

    @Override
    public void returnLoan(int loanId) {
        Loan loan = getLoanById(loanId);
        if (loan.getDueDate().after(new Date())) {loanView.showErrorMessage("devolução atrasada, uma multa será cobrada");}
        Book book = loan.getBook();
        bookDatabase.updateBook(book.getId(), book.getName(), book.getAuthor(), book.getYear(), book.getGenre(), book.getISBN(), book.getCopies()+1);
        loanDatabase.returnBook(loanId);
    }

    @Override
    public List<Loan> getActiveLoans() {
        return loanDatabase.getActiveLoans();
    }

    @Override
    public void refreshLoans() {
        loans = loanDatabase.getActiveLoans();
    }

    public Loan getLoanById(int id) {
        List<Loan> loans = loanDatabase.getActiveLoans();
        for (Loan loan : loans) {
            if (loan.getid() == id){
                return loan;
            }
        }
        return null;// nunca ocorre
    }
}