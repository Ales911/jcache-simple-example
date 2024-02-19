package by.lyofa.jcachesimpleexample;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class BookRepositoryTest {

    @InjectMocks
    private BookRepository bookRepository;

    @Test
    public void testAdd() {
        // Given
        final Book expected = new Book("isbn4", "author4", "title4");

        // When
        final Book actual = bookRepository.add(expected);

        // Then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindAll() {

        // When
        final List<Book> actual = bookRepository.findAll();

        // Then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(3, actual.size());
        Assertions.assertEquals("isbn3", actual.get(2).isbn());
        Assertions.assertEquals("title3", actual.get(2).title());
        Assertions.assertEquals("author3", actual.get(2).author());
    }

    @Test
    public void testFindByIsbn() {
        // Given
        final Book expected = new Book("isbn2", "author2", "title2");

        // When
        final Book actual = bookRepository.findByIsbn("isbn2");

        // Then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testFindByIsbnExpectedException() {
        // Given
        // When
        final IllegalArgumentException ex
                = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    bookRepository.findByIsbn("isbn22");
                });

        // Then
        Assertions.assertEquals("No book found for isbn: isbn22", ex.getMessage());
    }

    @Test
    public void testUpdate() {
        // Given
        final Book expected = new Book("isbn33", "author33", "title33");

        // When
        final Book actual = bookRepository.update("isbn3", expected);

        // Then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testUpdateExpectedException() {
        // Given
        // When
        final IllegalArgumentException ex
                = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    bookRepository.update("isbn22", new Book("isbn22", "author22", "title22"));
                });

        // Then
        Assertions.assertEquals("No book found for isbn: isbn22", ex.getMessage());
    }

    @Test
    public void tesRemove() {
        // Given
        // When
        bookRepository.remove("isbn2");

        // Then
        Assertions.assertEquals(2, bookRepository.findAll().size());
    }

    @Test
    void testRemoveExpectedException() {
        // Given
        // When
        final IllegalArgumentException ex
                = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    bookRepository.remove("isbn22");
                });

        // Then
        Assertions.assertEquals("No book found for isbn: isbn22", ex.getMessage());
    }
}