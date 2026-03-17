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
    
    // PRUEBA 1: Agregar libro duplicado debe aumentar la cantidad en uno
    @Test
    public void shouldIncreaseQuantityByOneWhenAddingDuplicateBook() {
        Book book = new Book("Clean Code", "Robert Martin", "978-0132350884");

        // Se agrega el libro por primera vez (cantidad = 1)
        boolean firstAdd = library.addBook(book);

        // Se agrega el mismo libro de nuevo (cantidad debe ser 2)
        boolean secondAdd = library.addBook(book);

        assertTrue(firstAdd, "La primera inserción debe retornar true");
        assertTrue(secondAdd, "La segunda inserción (duplicado) debe retornar true");
    }

    // PRUEBA 2: Préstamo de libro sin disponibilidad debe retornar null
    @Test
    public void shouldReturnNullWhenLoaningUnavailableBook() {
        // Registrar usuario
        User user = new User();
        user.setId("user-001");
        user.setName("Carlos");
        library.addUser(user);

        // Registrar libro (cantidad = 1) y prestarlo al usuario
        Book book = new Book("Clean Code", "Robert Martin", "978-0132350884");
        library.addBook(book);
        library.loanABook("user-001", "978-0132350884");

        // Intentar prestar el mismo libro cuando ya no hay disponibilidad
        User secondUser = new User();
        secondUser.setId("user-002");
        secondUser.setName("Ana");
        library.addUser(secondUser);

        Loan result = library.loanABook("user-002", "978-0132350884");

        assertNull(result, "Debe retornar null cuando el libro no está disponible");
    }
}