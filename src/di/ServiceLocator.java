package di;

//import for books
import feature.book.datasource.BookDAO;
import feature.book.datasource.BookDatabase;
import feature.book.datasource.BookSubscriber;
import feature.book.presentation.*;
//import for Users
import feature.user.datasource.UserDAO;
import feature.user.datasource.UserDatabase;
import feature.user.datasource.UserSubscriber;
import feature.user.presentation.*;

public class ServiceLocator {

    // Instancia para o singleton
    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if(instance == null) {
            instance = new ServiceLocator();
        }

        return instance;
    }
// Books ======================================================================
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
// Users ======================================================================
private UserDAO userDAO;

    private UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }

        return userDAO;
    }

    public UserDatabase getUserDatabase() {
        return getUserDAO();
    }

    public UserSubscriber getUserSubscriber() {
        return getUserDAO();
    }

    public UserController getUserController() {
        return new UserControllerImpl(getUserDatabase());
    }

    public UserView getUserView() {
        return new UserViewImpl(getUserSubscriber(), getUserController());
    }

    public UserEdit getUserEdit() {
        return new UserEditImpl(getUserController());
    }

    public HomeView getHomeView() {
        return new HomeViewImpl();
    }

    public LoginView getLoginView() {
        LoginView loginView = new LoginViewImpl();
        LoginController loginController = new LoginControllerImpl(getUserDAO(), loginView);
        loginView.setLoginController(loginController);
        return loginView;
    }
}
