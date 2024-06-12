package feature.book.presentation;

import feature.book.datasource.BookSubscriber;
import feature.book.model.Book;
import feature.book.datasource.BookListener;
import feature.book.services.BookServices;
import di.ServiceLocator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookViewImpl extends JFrame implements BookView, BookListener {
    private final DefaultTableModel tableModel;
    private final BookController bookController;

    public BookViewImpl(BookSubscriber bookSubscriber,BookController bookController) {
        setTitle("Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.bookController = bookController;
        bookController.setView(this);

        tableModel = new DefaultTableModel(new Object[]{"Título", "Autor", "Ano", "Gênero", "ISBN", "Und."}, 0);
        initializeUI();

        // Carregar livros do banco de dados
        loadBooks();
        bookSubscriber.subscribe(this);
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JTable bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Título:"));
        JTextField titleField = new JTextField(20);
        formPanel.add(titleField);

        formPanel.add(new JLabel("Autor:"));
        JTextField authorField = new JTextField(20);
        formPanel.add(authorField);

        formPanel.add(new JLabel("Ano:"));
        JTextField yearField = new JTextField(20);
        formPanel.add(yearField);

        formPanel.add(new JLabel("Gênero:"));
        JTextField genreField = new JTextField(20);
        formPanel.add(genreField);

        formPanel.add(new JLabel("ISBN:"));
        JTextField isbnField = new JTextField(20);
        formPanel.add(isbnField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton searchButton = new JButton("Pesquisar");
        searchButton.addActionListener(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = 0;
            try {
                year = yearField.getText().isEmpty() ? 0 : Integer.parseInt(yearField.getText());
            } catch (NumberFormatException ex){
                showErrorMessage("Valor Ano Invalido.");
            }
            String genre = genreField.getText();
            String ISBN = isbnField.getText();
            List<Book> fullList = bookController.getBooks();
            List<Book> books = BookServices.getBooksBySearch(title, author, year, genre, ISBN, fullList);
            updateBookTable(books);
        });
        buttonPanel.add(searchButton);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                ServiceLocator.getInstance().getBookEdit().open();
            });
        });
        buttonPanel.add(addButton);// somente se usuario for adm

        JButton editButton = new JButton("Editar");
        editButton.addActionListener(e -> {
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                String selectedISBN = (String) tableModel.getValueAt(selectedRow, 4);
                Book book = bookController.getBookByISBN(selectedISBN);
                SwingUtilities.invokeLater(() -> {
                    ServiceLocator.getInstance().getBookEdit().open(book);
                });
            } else {
                showErrorMessage("Selecione um livro para editar.");
            }
        });
        buttonPanel.add(editButton);// somente se usuario for adm

        JButton deleteButton = new JButton("Excluir");
        deleteButton.addActionListener(e -> {
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                String selectedISBN = (String) tableModel.getValueAt(selectedRow, 4);
                bookController.deleteBook(selectedISBN);
                updateData();
            } else {
                showErrorMessage("Selecione um livro para excluir.");
            }
        });
        buttonPanel.add(deleteButton);// somente se usuario for adm
        JButton refreshButton = new JButton("Recarregar");
        refreshButton.addActionListener(e -> {
            updateData();
        });
        buttonPanel.add(refreshButton);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadBooks() {
        List<Book> books = bookController.getBooks();
        updateBookTable(books);
    }

    private void updateBookTable(List<Book> books) {
        tableModel.setRowCount(0);
        for (Book book : books) {
            tableModel.addRow(new Object[]{book.getName(), book.getAuthor(), book.getYear(), book.getGenre(),book.getISBN(), book.getCopies() });
        }
    }

    @Override
    public void open() {
        setVisible(true);
    }

    @Override
    public void close() {
        dispose();
    }

    @Override
    public void minimize() {
        setVisible(false);
    }

    @Override
    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void updateData() {
        bookController.refreshBooks();
        loadBooks();
    }
}

