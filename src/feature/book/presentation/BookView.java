package feature.book.presentation;

public interface BookView {
    void open();
    void close();
    void minimize();
    void showErrorMessage(String msg);
}
