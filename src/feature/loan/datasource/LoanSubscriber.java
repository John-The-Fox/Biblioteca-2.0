package feature.loan.datasource;

import feature.loan.datasource.LoanListener;

public interface LoanSubscriber {
    void subscribe(LoanListener loanObserver);
}
