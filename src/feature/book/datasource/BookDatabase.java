package feature.book.datasource;

import feature.book.model.Book;

import java.util.List;

public interface BookDatabase {
    void insertBook(String name, String author, int year, String genre, String ISBN, int copies);
    void updateTask(int id, String name, String author, int year, String genre, String ISBN, int copies);
    List<Book> getBooks();
}
