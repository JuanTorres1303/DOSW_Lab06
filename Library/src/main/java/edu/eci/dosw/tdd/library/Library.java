package edu.eci.dosw.tdd.library;

import edu.eci.dosw.tdd.library.book.Book;
import edu.eci.dosw.tdd.library.loan.Loan;
import edu.eci.dosw.tdd.library.loan.LoanStatus;
import edu.eci.dosw.tdd.library.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private final List<User> users;
    private final Map<Book, String> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isEmpty()) {
            return false;
        }

        for (Book b : books.keySet()) {
            if (b.equals(book)) {
                b.incrementQuantity();
                return true;
            }
        }

        books.put(book, "AVAILABLE");
        return true;
    }

    public Loan loanABook(String userId, String isbn) {
        if (userId == null || isbn == null) return null;

        User user = users.stream()
                .filter(u -> userId.equals(u.getId()))
                .findFirst()
                .orElse(null);

        if (user == null) return null;

        Book book = books.keySet().stream()
                .filter(b -> isbn.equals(b.getIsbn()))
                .findFirst()
                .orElse(null);

        if (book == null || book.getQuantity() <= 0) return null;

        boolean alreadyLoaned = loans.stream()
                .anyMatch(l -> l.getUser().getId().equals(userId)
                        && l.getBook().getIsbn().equals(isbn)
                        && l.getStatus() == LoanStatus.ACTIVE);

        if (alreadyLoaned) return null;

        book.decrementQuantity();

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDateTime.now());
        loan.setStatus(LoanStatus.ACTIVE);

        loans.add(loan);

        return loan;
    }

    public Loan returnLoan(Loan loan) {
        if (loan == null || !loans.contains(loan)) return null;

        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDateTime.now());

        Book book = loan.getBook();
        for (Book b : books.keySet()) {
            if (b.equals(book)) {
                b.incrementQuantity();
                break;
            }
        }

        return loan;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }
}