package feature.book.presentation;

import feature.book.model.Book;

public interface BookEdit {
    void open();
    void open(Book book);
    void close();
    void setBookDetails(Book book);
    void showErrorMessage(String message);
}
