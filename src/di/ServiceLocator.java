package di;

import feature.book.datasource.BookDAO;
import feature.book.datasource.BookDatabase;
import feature.book.datasource.BookSubscriber;
import feature.book.presentation.*;

public class ServiceLocator {

    // Instancia para o singleton
    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if(instance == null) {
            instance = new ServiceLocator();
        }

        return instance;
    }

    private BookDAO BookDAO;

    private BookDAO getBookDao() {
        if(BookDAO == null) {
            BookDAO = new BookDAO();
        }

        return BookDAO;
    }

    public BookDatabase getBookDatabase() {
        return getBookDao();
    }

    public BookSubscriber getBookSubscriber() {
        return getBookDao();
    }

    public BookController getBookController() {
        return new BookControllerImpl(getBookDatabase());
    }

    public BookView getBookView() {
        return new BookViewImpl(getBookSubscriber(), getBookController());
    }

    public BookEdit getBookEdit() {
        return new BookEditImpl(getBookController());
    }
}
