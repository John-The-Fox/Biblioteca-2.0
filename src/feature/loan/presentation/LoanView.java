package feature.loan.presentation;

import feature.book.model.Book;

public interface LoanView {
    void open();
    void open(Book book);
    void close();
    void showErrorMessage(String msg);
}