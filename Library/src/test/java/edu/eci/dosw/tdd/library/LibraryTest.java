package edu.eci.dosw.tdd.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;
    private Book book;
    private User user;

    @BeforeEach
    void setUp() {
        library = new Library();
        book = new Book("Test", "Author", "123");
        user = new User("Daniel", "1");
        library.addUser(user);
    }

    @Test
    void shouldAddBookSuccessfully() {
        assertTrue(library.addBook(book));
    }

    @Test
    void shouldNotAddBookWithNull() {
        assertFalse(library.addBook(null));
    }

    @Test
    void shouldNotAddBookWithEmptyIsbn() {
        Book invalid = new Book("Test", "Author", "");
        assertFalse(library.addBook(invalid));
    }

    @Test
    void shouldIncreaseQuantityWhenAddingDuplicateBook() {
        library.addBook(book);
        library.addBook(book);
        assertEquals(2, book.getQuantity());
    }

    @Test
    void shouldLoanBookSuccessfully() {
        library.addBook(book);
        Loan loan = library.loanABook("1", "123");

        assertNotNull(loan);
        assertEquals(book, loan.getBook());
        assertEquals(user, loan.getUser());
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
    }

    @Test
    void shouldDecreaseQuantityWhenLoaning() {
        library.addBook(book);
        library.loanABook("1", "123");

        assertEquals(0, book.getQuantity());
    }

    @Test
    void shouldNotLoanIfUserDoesNotExist() {
        library.addBook(book);
        assertNull(library.loanABook("999", "123"));
    }

    @Test
    void shouldNotLoanIfBookDoesNotExist() {
        assertNull(library.loanABook("1", "999"));
    }

    @Test
    void shouldNotLoanSameBookTwiceToSameUser() {
        library.addBook(book);
        library.addBook(book);

        library.loanABook("1", "123");
        Loan second = library.loanABook("1", "123");

        assertNull(second);
    }

    @Test
    void shouldReturnLoanSuccessfully() {
        library.addBook(book);
        Loan loan = library.loanABook("1", "123");

        Loan returned = library.returnLoan(loan);

        assertNotNull(returned);
        assertEquals(LoanStatus.RETURNED, returned.getStatus());
    }

    @Test
    void shouldIncreaseQuantityWhenReturning() {
        library.addBook(book);
        Loan loan = library.loanABook("1", "123");

        library.returnLoan(loan);

        assertEquals(1, book.getQuantity());
    }

    @Test
    void shouldNotReturnNullLoan() {
        assertNull(library.returnLoan(null));
    }

    @Test
    void shouldNotReturnLoanNotInSystem() {
        assertNull(library.returnLoan(new Loan()));
    }

    @Test
    void shouldCreateUserCorrectly() {
        User u = new User("Daniel", "1");
        assertEquals("Daniel", u.getName());
        assertEquals("1", u.getId());
    }

    @Test
    void shouldUpdateUserFields() {
        User u = new User("Daniel", "1");
        u.setName("Carlos");
        u.setId("2");

        assertEquals("Carlos", u.getName());
        assertEquals("2", u.getId());
    }

    @Test
    void usersWithSameIdShouldBeEqual() {
        User u1 = new User("A", "1");
        User u2 = new User("B", "1");

        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    void bookEqualsShouldWorkByIsbn() {
        Book b1 = new Book("A", "Auth", "123");
        Book b2 = new Book("B", "Auth", "123");

        assertEquals(b1, b2);
    }

    @Test
    void loanStatusHelpersShouldWork() {
        Loan loan = new Loan();

        loan.setStatus(LoanStatus.ACTIVE);
        assertTrue(loan.isActive());
        assertFalse(loan.isReturned());

        loan.setStatus(LoanStatus.RETURNED);
        assertTrue(loan.isReturned());
        assertFalse(loan.isActive());
    }

    @Test
    void loanEqualsShouldWork() {
        Loan l1 = new Loan();
        Loan l2 = new Loan();

        assertEquals(l1, l2);
    }
}