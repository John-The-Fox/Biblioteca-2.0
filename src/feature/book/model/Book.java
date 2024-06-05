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

    public Book() {}

    public Book(int id, String name, String author, int year, String genre, String ISBN) {
        this.id = id;
        this.name = name;
        this.author =author;
        this.year = year;
        this.genre = genre;
        this.ISBN = ISBN;
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
}
