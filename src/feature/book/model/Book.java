package feature.book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private int id;
    private String ISBN;
    private String name;
    private String author;
    private int year;
    private String genre;
    private int copies;

    public Book() {}

    public Book(int id, String name, String author, int year, String genre, String ISBN, int copies) {
        this.id = id;
        this.name = name;
        this.author =author;
        this.year = year;
        this.genre = genre;
        this.ISBN = ISBN;
        this.copies = copies;
    }
    public Book(String name, String author, int year, String genre, String ISBN, int copies) {
        this.name = name;
        this.author =author;
        this.year = year;
        this.genre = genre;
        this.ISBN = ISBN;
        this.copies = copies;
    }

    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }
    public String getISBN() {
        return ISBN;
    }
    public int getYear() {
        return year;
    }
    public int getCopies() {
        return copies;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setISBN(int ISBN) {
        this.ISBN = this.ISBN;
    }
    public void setCopies(int copies) {
        this.copies = copies;
    }
}
