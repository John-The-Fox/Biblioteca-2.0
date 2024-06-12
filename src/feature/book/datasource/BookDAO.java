package feature.book.datasource;

import feature.book.model.Book;
import infrastructure.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class BookDAO implements BookDatabase, BookSubscriber {
    private final List<BookListener> listeners;

    public BookDAO() {
        this(new ArrayList<>());
    }

    public BookDAO(List<BookListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void subscribe(BookListener bookListener) {
        listeners.add(bookListener);
    }

    private void notifyDataChanged() {
        for (BookListener listener : listeners) {
            listener.updateData();
        }
    }

    @Override
    public void insertBook(String name, String author, int year, String genre, String ISBN, int copies) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var book = new Book(name, author, year, genre, ISBN, copies);
                session.persist(book);
            });
            System.out.println("feature.book.model.Book inserted successfully.");
            notifyDataChanged();
        } catch (Exception e) {
            System.out.println("feature.book.model.Book insertion failed: " + e.getMessage());
        }
    }

    @Override
    public void updateBook(int id, String name, String author, int year, String genre, String ISBN, int copies) {
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var book = session.get(Book.class, id);
                book.setName(name);
                book.setAuthor(author);
                book.setYear(year);
                book.setGenre(genre);
                book.setISBN(ISBN);
                book.setCopies(copies);
            });
            System.out.println("feature.book.model.Book updated successfully.");
            notifyDataChanged();
        } catch (Exception e) {
            System.out.println("feature.book.model.Book update failed: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getBooks() {
        List<Book> result = new ArrayList<>();
        try {
            result = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createQuery("from Book").list();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteBook(int id) {
        try{
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var book = session.get(Book.class, id);
                if (book != null) {
                    session.remove(book);
                    System.out.println("Book deleted successfully.");
                } else {
                    System.out.println("Book with id " + id + " not found.");
                }
            });
            notifyDataChanged();
        } catch (Exception e) {
            System.out.println("Book deletion failed: " + e.getMessage());
        }
    }

}
