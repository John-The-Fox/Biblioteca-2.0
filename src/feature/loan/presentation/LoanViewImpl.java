package feature.loan.presentation;

import feature.book.model.Book;
import feature.loan.datasource.LoanListener;
import feature.loan.model.Loan;
import feature.loan.datasource.LoanSubscriber;
import di.ServiceLocator;
import feature.loan.services.LoanServices;
import global.Globals;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LoanViewImpl extends JFrame implements LoanView, LoanListener {
    private final DefaultTableModel tableModel;
    private final LoanController loanController;

    public LoanViewImpl(LoanSubscriber loanSubscriber, LoanController loanController) {
        setTitle("Empréstimos Ativos");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.loanController = loanController;
        loanController.setView(this);

        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Título do Livro", "Autor", "Data Empréstimo", "Data Devolução",
                "Nome Aluno", "RA", "Email", "Telefone", "Endereço"
        }, 0);
        initializeUI();

        // Carregar empréstimos do banco de dados
        loadLoans();
        loanSubscriber.subscribe(this);
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JTable loanTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(loanTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton returnButton = new JButton("Devolver");
        returnButton.addActionListener(e -> {
            int selectedRow = loanTable.getSelectedRow();
            if (selectedRow != -1) {
                int loanId = (int) tableModel.getValueAt(selectedRow, 0);
                loanController.returnLoan(loanId);
                updateData();
                close();
            } else {
                showErrorMessage("Selecione um empréstimo para devolver.");
            }
        });
        buttonPanel.add(returnButton);

        JButton refreshButton = new JButton("Recarregar");
        refreshButton.addActionListener(e -> {
            updateData();
        });
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadLoans() {
        List<Loan> loans = loanController.getActiveLoans();
        updateLoanTable(loans);
    }

    private void updateLoanTable(List<Loan> loans) {
        tableModel.setRowCount(0);
        for (Loan loan : loans) {
            tableModel.addRow(new Object[]{
                    loan.getid(),loan.getBook().getName(), loan.getBook().getAuthor(),
                    loan.getLoanDate(), loan.getDueDate(), loan.getName(),
                    loan.getRa(), loan.getEmail(), loan.getPhone(), loan.getAddress()
            });
        }
    }

    @Override
    public void open() {
        setVisible(true);
    }

    @Override
    public void open(Book book) {
        updateLoanTable(LoanServices.getLoansByBook(book,loanController.getActiveLoans()));
        setVisible(true);
    }

    @Override
    public void close() {
        dispose();
    }


    @Override
    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void updateData() {
        loanController.refreshLoans();
        loadLoans();
    }
}