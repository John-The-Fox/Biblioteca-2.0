package feature.book.presentation;

import di.ServiceLocator;
import feature.book.datasource.BookDatabase;
import feature.book.model.Book;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BookControllerImpl implements BookController {
    private BookView bookView;
    private BookDatabase bookDatabase;
    private List<Book> books = new ArrayList<>();

    public BookControllerImpl(BookDatabase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    @Override
    public void setView(BookView view){

    }

    @Override
    public void addBook(String name, String author, int year, String genre, String ISBN, int copies){
        bookDatabase.insertBook(name, author, year, genre, ISBN, copies);
    }

    @Override
    public void updateBook(int id, String name, String author, int year, String genre, String ISBN, int copies){

    }

    @Override
    public List<Book> getBooks(){
        if (books.isEmpty()){
            books = bookDatabase.getBooks();
        }
        return books;
    }

    @Override
    public void deleteBook(String ISBN){
        List<Book> books = getBooks();
        int id = -1;
        for (Book book : books){
            if (book.getISBN().equals(ISBN)){
                id = book.getId();
            }
        }
        bookDatabase.deleteBook(id);
    }

    @Override
    public void editBook(int id, String name, String author, int year, String genre, String ISBN){

    }

    @Override
    public void refreshBooks(){
        books = bookDatabase.getBooks();
    }

    @Override
    public Book getBookByISBN(String ISBN){
        List<Book> books = getBooks();
        for (Book book : books){
            if (book.getISBN().equals(ISBN)){
                return book;
            }
        }
        return null;// esse caso nunca deve ocorrer
    }
}
