package feature.book.presentation;

import feature.book.model.Book;

import javax.swing.*;
import java.awt.*;

public class BookEditImpl extends JFrame implements BookEdit {
    private final BookController bookController;
    private final JTextField nameField;
    private final JTextField authorField;
    private final JTextField yearField;
    private final JTextField genreField;
    private final JTextField isbnField;
    private final JTextField copiesField;
    private final JButton saveButton;
    private final JButton cancelButton;
    private int bookId =-1;

    public BookEditImpl(BookController bookController) {
        this.bookController = bookController;
        setTitle("Editar/Adicionar Livro");
        setSize(400, 300);
        setLayout(new GridLayout(7, 2));

        // Criação dos campos
        add(new JLabel("Nome:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Autor:"));
        authorField = new JTextField();
        add(authorField);

        add(new JLabel("Ano:"));
        yearField = new JTextField();
        add(yearField);

        add(new JLabel("Gênero:"));
        genreField = new JTextField();
        add(genreField);

        add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        add(isbnField);

        add(new JLabel("Cópias:"));
        copiesField = new JTextField();
        add(copiesField);

        // Botões
        saveButton = new JButton("Salvar");
        saveButton.addActionListener(e -> saveBook(bookId));
        add(saveButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> close());
        add(cancelButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void open() {
        setVisible(true);
        setTitle("Adicionar Livro");
        clearFields();
    }

    @Override
    public void open(Book book) {
        setBookDetails(book);
        bookId = book.getId();
        setTitle("Editar Livro ID: " + book.getId());
        setVisible(true);

    }

    @Override
    public void close() {
        setVisible(false);
        dispose();
    }

    @Override
    public void setBookDetails(Book book) {
        nameField.setText(book.getName());
        authorField.setText(book.getAuthor());
        yearField.setText(String.valueOf(book.getYear()));
        genreField.setText(book.getGenre());
        isbnField.setText(book.getISBN());
        copiesField.setText(String.valueOf(book.getCopies()));
        //add(new JLabel("ID:" + book.getId()));
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void saveBook(int bookId) {
        // Obter dados dos campos de texto
        String name = nameField.getText();
        String author = authorField.getText();
        int year = 0;
        try {
            year = yearField.getText().isEmpty() ? 0 : Integer.parseInt(yearField.getText());
        } catch (NumberFormatException ex){
            showErrorMessage("Valor Ano Invalido.");
        }
        String genre = genreField.getText();
        String ISBN = isbnField.getText();
        int copies = Integer.parseInt(copiesField.getText());
        if (bookId == -1) {
            bookController.addBook(name, author, year, genre, ISBN, copies);
        } else{
            bookController.editBook(bookId, name, author, year, genre, ISBN, copies);
        }
        bookController.refreshBooks();
        // Notificar o usuário que o livro foi salvo com sucesso
        JOptionPane.showMessageDialog(this, "Livro salvo com sucesso!");
        close();
    }
    private void clearFields() {
        nameField.setText("");
        authorField.setText("");
        yearField.setText("");
        genreField.setText("");
        isbnField.setText("");
        copiesField.setText("");
    }
}
