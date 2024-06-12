package feature.loan.presentation;

import feature.book.model.Book;
import feature.user.model.User;
import feature.loan.model.Loan;
import feature.loan.services.LoanServices;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class LoanAddImpl extends JFrame implements LoanAdd {
    private final LoanController loanController;
    private final JLabel bookTitleLabel;
    private final JLabel bookAuthorLabel;
    private final JTextField studentNameField;
    private final JTextField studentRaField;
    private final JTextField studentEmailField;
    private final JTextField studentPhoneField;
    private final JTextField studentAddressField;
    private final JTextField daysField;
    private final JButton saveButton;
    private final JButton cancelButton;
    private Book currentBook;
    private User currentUser;

    public LoanAddImpl(LoanController loanController) {
        this.loanController = loanController;
        setTitle("Adicionar Empréstimo");
        setSize(400, 400);
        setLayout(new GridLayout(9, 2));

        // Campos para informações do livro (somente leitura)
        add(new JLabel("Livro:"));
        bookTitleLabel = new JLabel();
        add(bookTitleLabel);

        add(new JLabel("Autor:"));
        bookAuthorLabel = new JLabel();
        add(bookAuthorLabel);

        add(new JLabel("Dias:"));
        daysField = new JTextField();
        add(daysField);

        // Campos para informações do aluno
        add(new JLabel("Nome do Aluno:"));
        studentNameField = new JTextField();
        add(studentNameField);

        add(new JLabel("RA do Aluno:"));
        studentRaField = new JTextField();
        add(studentRaField);

        add(new JLabel("Email do Aluno:"));
        studentEmailField = new JTextField();
        add(studentEmailField);

        add(new JLabel("Telefone do Aluno:"));
        studentPhoneField = new JTextField();
        add(studentPhoneField);

        add(new JLabel("Endereço do Aluno:"));
        studentAddressField = new JTextField();
        add(studentAddressField);

        // Botões
        saveButton = new JButton("Salvar");
        saveButton.addActionListener(e -> saveLoan());
        add(saveButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> close());
        add(cancelButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void open(Book book, User user) {
        this.currentBook = book;
        this.currentUser = user;

        bookTitleLabel.setText(book.getName());
        bookAuthorLabel.setText(book.getAuthor());

        clearFields();
        setVisible(true);
    }

    @Override
    public void close() {
        setVisible(false);
        dispose();
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void saveLoan() {
        // Obter dados dos campos de texto
        if (currentBook.getCopies() >0) {
            String studentName = studentNameField.getText();
            String studentRa = studentRaField.getText();
            String studentEmail = studentEmailField.getText();
            String studentPhone = studentPhoneField.getText();
            String studentAddress = studentAddressField.getText();
            int days = 10;
            try {
                days = daysField.getText().isEmpty() ? 0 : Integer.parseInt(daysField.getText());
            } catch (NumberFormatException ex) {
                showErrorMessage("Valor Dias Invalido.");
            }
            // Validar os campos
            if (studentName.isEmpty() || studentRa.isEmpty() || studentEmail.isEmpty() || studentPhone.isEmpty() || studentAddress.isEmpty()) {
                showErrorMessage("Todos os campos devem ser preenchidos.");
                return;
            }
            Date dueDate = LoanServices.calculateDueDate(days);
            boolean returned = false;

            // Salvar o empréstimo usando o controlador
            loanController.rentBook(currentBook, currentUser, new Date(), dueDate, returned, studentName, studentRa, studentEmail, studentPhone, studentAddress);

            // Notificar o usuário que o empréstimo foi salvo com sucesso
            JOptionPane.showMessageDialog(this, "Empréstimo salvo com sucesso!");
            close();
        }else{
            JOptionPane.showMessageDialog(this, "Sem copias disponiveis!");
            close();
        }
    }

    private void clearFields() {
        studentNameField.setText("");
        studentRaField.setText("");
        studentEmailField.setText("");
        studentPhoneField.setText("");
        studentAddressField.setText("");
    }
}
