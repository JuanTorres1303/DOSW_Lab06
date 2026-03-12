package edu.eci.dosw.tdd.library;

import edu.eci.dosw.tdd.library.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
    }

    @Test
    public void shouldReturnFalseWhenAddingNullBook() {
        boolean result = library.addBook(null);
        assertFalse(result);
    }

    @Test
    void testAddBookWithEmptyIsbnReturnsFalse() {
        Library library = new Library();
        Book book = new Book("Clean Code", "Robert Martin", "");
        boolean result = library.addBook(book);
        assertFalse(result);
    }


    @Test
    void testReturnLoanChangesStatusToReturned() {
        Library library = new Library();
    
        User user = new User();
        user.setId("U01");
        user.setName("Ahumada");
        library.addUser(user);
    
        Book book = new Book("Clean Code", "Robert Martin", "978-01");
        library.addBook(book);
    
        Loan loan = library.loanABook("U01", "978-01");
        Loan returned = library.returnLoan(loan);
    
        assertNotNull(returned);
        assertEquals(LoanStatus.RETURNED, returned.getStatus());
    }
    
}