package feature.loan.presentation;

public class LoanViewImpl implements LoanView {
    private final LoanController loanController;

    public LoanViewImpl(LoanController loanController) {
        this.loanController = loanController;
    }

    @Override
    public void show() {
        // Implementar a lógica da tela de empréstimo e devolução.
    }
}