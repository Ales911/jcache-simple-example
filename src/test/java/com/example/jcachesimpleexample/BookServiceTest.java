package com.example.jcachesimpleexample;

import com.example.jcachesimpleexample.Book;
import com.example.jcachesimpleexample.BookRepository;
import com.example.jcachesimpleexample.BookService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;
    
    private final Book book = new Book("isbn1", "author1", "title1");

    @Test
    public void testAdd() {
        // Given

        // When
        Mockito.when(bookRepository.add(book)).thenReturn(book);

        final Book result = bookService.add(book.isbn(), book);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(book, result);

        Mockito.verify(bookRepository, Mockito.times(1)).add(book);
    }
    
    @Test
    public void testFindByIsbn() {
        // Given

        // When
        Mockito.when(bookRepository.findByIsbn(book.isbn())).thenReturn(book);

        final Book result = bookService.findByIsbn(book.isbn());

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(book, result);

        Mockito.verify(bookRepository, Mockito.times(1)).findByIsbn(book.isbn());
    }
    
    @Test
    public void testFindAll() {
        // Given
        final List<Book> books = List.of(book);

        // When
        Mockito.when(bookRepository.findAll()).thenReturn(books);

        final List<Book> result = bookService.findAll();

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(books, result);

        Mockito.verify(bookRepository, Mockito.times(1)).findAll();
    }
    
    @Test
    public void testUpdateByIsbn() {
        // Given

        // When
        Mockito.when(bookRepository.update(book.isbn(), book)).thenReturn(book);

        final Book result = bookService.update(book.isbn(), book);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(book, result);

        Mockito.verify(bookRepository, Mockito.times(1)).update(book.isbn(), book);
    }
    
    @Test
    public void testRemove() {
        // Given
        
        // When
        // Mockito.doNothing().when(bookRepository).remove(ArgumentMatchers.isA(String.class));
        Assertions.assertDoesNotThrow(() -> bookService.remove(book.isbn()));
        
        Mockito.verify(bookRepository, Mockito.times(1)).remove(book.isbn());
    }

}