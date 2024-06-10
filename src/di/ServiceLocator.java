package di;

import feature.book.datasource.BookDAO;
import feature.book.datasource.BookDatabase;
import feature.book.datasource.BookSubscriber;
import feature.book.presentation.BookController;
import feature.book.presentation.BookControllerImpl;
import feature.book.presentation.BookView;
import feature.book.presentation.BookViewImpl;
import feature.user.datasource.UserDAO;
import feature.user.datasource.UserDatabase;
import feature.user.datasource.UserSubscriber;
import feature.user.presentation.UserController;
import feature.user.presentation.UserControllerImpl;
import feature.user.presentation.UserView;
import feature.user.presentation.UserViewImpl;
import feature.loan.datasource.LoanDAO;
import feature.loan.datasource.LoanDatabase;
import feature.loan.datasource.LoanSubscriber;
import feature.loan.presentation.LoanController;
import feature.loan.presentation.LoanControllerImpl;
import feature.loan.presentation.LoanView;
import feature.loan.presentation.LoanViewImpl;

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
}
