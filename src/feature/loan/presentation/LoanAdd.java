package feature.loan.presentation;

import feature.book.model.Book;
import feature.user.model.User;

public interface LoanAdd {
    void open(Book book, User user);
    void close();
    void showErrorMessage(String message);
}
