package feature.loan.presentation;

import feature.loan.model.Loan;

public interface LoanController {
    void issueLoan(int userId, int bookId, int days);
    void returnBook(int loanId);
}