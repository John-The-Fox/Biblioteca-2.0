package feature.loan.services;

import feature.book.model.Book;
import feature.loan.model.Loan;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class LoanServices {
    public static Date calculateDueDate(int days){
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDateLocal  = currentDate.plusDays(days);
        return Date.from(dueDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static List<Loan> getLoansByBook(Book book, List<Loan> fullList) {
        List<Loan> resultLoans = new ArrayList<>();
        for (Loan loan : fullList) {
            if (loan.getBook().getISBN().equals(book.getISBN())) {
                resultLoans.add(loan);
            }
        }
        return resultLoans;
    }
}
