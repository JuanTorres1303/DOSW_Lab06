package edu.eci.dosw.tdd.library;

import java.util.Objects;

public class Book {
    private final String tittle;
    private final String author;
    private final String isbn;
    private int quantity;

    public Book(String tittle, String author, String isbn) {
        this.tittle = tittle;
        this.author = author;
        this.isbn = isbn;
        this.quantity = 1;
    }

    public String getTittle() {
        return tittle;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book other = (Book) obj;
        return Objects.equals(isbn, other.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}