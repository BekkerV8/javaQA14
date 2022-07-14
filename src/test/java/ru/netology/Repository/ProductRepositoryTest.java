package ru.netology.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private Book book1 = new Book(1, "МАСТЕР И МАРГАРИТА", 10, "БУЛГАКОВ М.А.");
    private Book book2 = new Book(2, "ПРЕСТУПЛЕНИЕ И НАКАЗАНИЕ", 20, "ДОСТОЕВСКИЙ Ф.М.");
    private Book book3 = new Book(3, "ВОЙНА И МИР", 30, "ТОЛСТОЙ Л.Н.");


    @BeforeEach
    public void save() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
    }

    @Test
    void shouldRemoveByExistingId() {
        repository.removeById(1);

        Product[] expected = new Product[]{book2, book3};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldRemoveByNotExistingId() {
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(4);
        });
    }
}