package feature.book.services;

import feature.book.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookServices {
    public List<Book> getBooksBySearch(String name, String author, int year, String genre, String ISBN, List<Book> fullLIst) {
        List<Book> resultBooks = new ArrayList<>();
        for (Book book : fullLIst) {
            if ((name.isEmpty() || name.equalsIgnoreCase(book.getName())) &&
                    (author.isEmpty() || author.equalsIgnoreCase(book.getAuthor())) &&
                    (year == 0 || year == book.getYear()) &&
                    (genre.isEmpty() || genre.equalsIgnoreCase(book.getGenre())) &&
                    (ISBN.isEmpty() || ISBN.equals(book.getISBN()))) {
                resultBooks.add(book);
            }
        }
        return resultBooks;
    }
}
