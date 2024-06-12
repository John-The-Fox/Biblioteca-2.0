package feature.loan.datasource;

import feature.book.model.Book;
import feature.loan.model.Loan;
import feature.user.model.User;
import infrastructure.DatabaseManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanDAO implements LoanDatabase, LoanSubscriber {

    private final List<LoanListener> listeners;

    public LoanDAO() {
        this(new ArrayList<>());
    }

    public LoanDAO(List<LoanListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void subscribe(LoanListener loanListener) {
        listeners.add(loanListener);
    }

    private void notifyDataChanged() {
        for (LoanListener listener : listeners) {
            listener.updateData();
        }
    }

    @Override
    public void rentBook(Book book, User user, Date loanDate, Date dueDate, boolean returned, String name, String ra, String email, String phone, String address){
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var loan = new Loan(user, book, loanDate, dueDate, returned, name, ra, email, phone, address);
                session.persist(loan);
            });
            System.out.println("feature.loan.model.Loan inserted successfully.");
            notifyDataChanged();
        } catch (Exception e) {
            System.out.println("feature.loan.model.Loan insertion failed: " + e.getMessage());
        }
        notifyDataChanged();
    }

    @Override
    public void returnBook(int id){
        try {
            DatabaseManager.getDatabaseSessionFactory().inTransaction(session -> {
                var loan = session.get(Loan.class, id);
                loan.setReturned(true);
            });
            System.out.println("feature.loan.model.Loan updated successfully.");
            notifyDataChanged();
        } catch (Exception e) {
            System.out.println("feature.loan.model.Loan update failed: " + e.getMessage());
        }
        notifyDataChanged();
    }
    
    @Override
    public List<Loan> getActiveLoans(){

        List<Loan> loans = new ArrayList<>();
        List<Loan> result = new ArrayList<>();
        try {
            loans = DatabaseManager.getDatabaseSessionFactory().fromTransaction(session -> {
                return session.createQuery("from Loan").list();
            });
            for (Loan loan : loans){
                if (loan.isReturned() == false){
                    result.add(loan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}