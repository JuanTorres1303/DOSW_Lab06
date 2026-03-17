package edu.eci.dosw.tdd.library;

import edu.eci.dosw.tdd.library.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    void shouldReturnFalseWhenAddingNullBook() {
        boolean result = library.addBook(null);
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenAddingBookWithEmptyIsbn() {
        Book book = new Book("Clean Code", "Robert Martin", "");
        boolean result = library.addBook(book);
        assertFalse(result);
    }

    @Test
    void shouldIncreaseQuantityWhenAddingDuplicateBook() {
        Book book = new Book("Clean Code", "Robert Martin", "978-0132350884");

        boolean firstAdd = library.addBook(book);
        boolean secondAdd = library.addBook(book);

        assertTrue(firstAdd);
        assertTrue(secondAdd);
    }

    @Test
    void shouldReturnNullWhenUserAlreadyHasActiveLoanForSameBook() {
        User user = new User("Carlos", "user-001");
        library.addUser(user);

        Book book = new Book("Clean Code", "Robert Martin", "978-0132350884");
        library.addBook(book);

        library.loanABook("user-001", "978-0132350884");
        Loan result = library.loanABook("user-001", "978-0132350884");

        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenLoaningUnavailableBook() {
        User user1 = new User("Carlos", "user-001");
        library.addUser(user1);

        Book book = new Book("Clean Code", "Robert Martin", "978-0132350884");
        library.addBook(book);

        library.loanABook("user-001", "978-0132350884");

        User user2 = new User("Ana", "user-002");
        library.addUser(user2);

        Loan result = library.loanABook("user-002", "978-0132350884");

        assertNull(result);
    }

    @Test
    void shouldLoanBookSuccessfully() {
        User user = new User("Camilo Leon", "CL123456789");
        library.addUser(user);

        Book book = new Book("El coronel no tiene quien le escriba", "Gabriel Garcia Marquez", "bf1212354586913");
        library.addBook(book);

        Loan loan = library.loanABook(user.getId(), book.getIsbn());

        assertNotNull(loan);
    }

    @Test
    void shouldReturnLoanSuccessfully() {
        User user = new User("Camilo Leon", "CL123456789");
        library.addUser(user);

        Book book = new Book("Clean Code", "Robert Martin", "978-01");
        library.addBook(book);

        Loan loan = library.loanABook("CL123456789", "978-01");
        Loan returned = library.returnLoan(loan);

        assertNotNull(returned);
        assertEquals(LoanStatus.RETURNED, returned.getStatus());
    }
}