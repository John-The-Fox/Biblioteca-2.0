package feature.book.presentation;

import feature.book.model.Book;

import java.util.List;

public interface BookController {
    void setView(BookView view);

    void addBook(String name, String author, int year, String genre, String ISBN, int copies);

    List<Book> getBooks();

    void deleteBook(String ISBN);

    void editBook(int id, String name, String author, int year, String genre, String ISBN, int copies);

    void refreshBooks();

    Book getBookByISBN(String ISBN);

    void rentBook(String ISBN);

    void returnBook(String ISBN);

    void returnBook();

}
