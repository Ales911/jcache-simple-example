package by.lyofa.jcachesimpleexample;

import java.util.List;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    
    @Autowired
    private BookRepository bookRepository;
    
    @CacheResult(cacheName = "books", skipGet = true)
    public Book add(@CacheKey String isbn, Book book) {
        logger.info("add a book with isbn: " + book.isbn());
        return bookRepository.add(book);
    }
    
    @CacheResult(cacheName = "books", exceptionCacheName = "failures", cachedExceptions = IllegalArgumentException.class)
    public Book findByIsbn(@CacheKey String isbn) {
        logger.info("finding book by isbn: " + isbn);
        return bookRepository.findByIsbn(isbn);
    }
    
    public List<Book> findAll() {
        logger.info("findAll(): ");
        return bookRepository.findAll();
    }
    
    @CachePut(cacheName = "books", afterInvocation = true)
    public Book update(String isbn, @CacheValue Book book) {
        logger.info("update book by isbn: " + isbn);
        return bookRepository.update(isbn, book);
    }
    
    @CacheRemove(cacheName = "books", afterInvocation = true)
    public void remove(@CacheKey String isbn) {
        logger.info("remove book by isbn: " + isbn);
        bookRepository.remove(isbn);
    }

}