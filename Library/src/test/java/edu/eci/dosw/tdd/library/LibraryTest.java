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
}