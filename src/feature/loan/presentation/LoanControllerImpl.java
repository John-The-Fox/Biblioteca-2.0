package feature.loan.presentation;

import feature.loan.model.Loan;
import feature.loan.datasource.LoanDAO;

public class LoanControllerImpl implements LoanController {
    private final LoanDAO loanDAO;

    public LoanControllerImpl(LoanDAO loanDAO) {
        this.loanDAO = loanDAO;
    }

    @Override
    public void issueLoan(int userId, int bookId, int days) {
        // Implementar a lógica de emissão de empréstimo.
    }

    @Override
    public void returnBook(int loanId) {
        // Implementar a lógica de devolução de livro.
    }
}