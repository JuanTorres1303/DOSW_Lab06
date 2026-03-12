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
    public void loanABook() {
        final String respuesta = "PRESTAMO EXITOSO CON USUARIO VALIDO Y LIBRO DISPONIBLE";
        Library library = new Library();
        Book book = new Book("El coronel no tiene quien le escriba", "Gabriel Garcia Marquez", "bf1212354586913");
        User user = new User("Camilo Leon", "CL123456789");
        library.addBook(book);
        library.users.add(user);

        if (library.loanABook(user.getId(), book.getIsbn()))
            System.out.println(respuesta);
        else
            fail("FALLO AL PRESTAR EL LIBRO");
    }

    @Test
    public void returnLoan() {
        final String respuesta = "REGRESO EXITOSO";
        Library library = new Library();
        Book book = new Book("El coronel no tiene quien le escriba", "Gabriel Garcia Marquez", "bf1212354586913");
        User user = new User("Camilo Leon", "CL123456789");
        library.addBook(book);
        library.users.add(user);

        if (library.returnLoan(user.getId(), book.getIsbn()))
            book.addBook(book);
        else
            fail("FALLO AL REGRESAR EL LIBRO");
    }

}