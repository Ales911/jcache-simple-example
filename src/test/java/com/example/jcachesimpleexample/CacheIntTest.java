package com.example.jcachesimpleexample;

import javax.cache.Cache;
import javax.cache.CacheManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheIntTest {

    private static final String CACHE_NAME = "books";

    @Autowired
    private BookService bookService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void findByNameCacheTest() {
        // Given
        final String isbn1 = "isbn1";
        Assertions.assertFalse(isInCache(isbn1));

        // When
        Assertions.assertNotNull(bookService.findByIsbn(isbn1));

        // Then
        Assertions.assertTrue(isInCache(isbn1));
    }

    @Test
    public void removeCacheTest() {
        // Given
        final String isbn2 = "isbn2";
        Assertions.assertFalse(isInCache(isbn2));
        Assertions.assertNotNull(bookService.findByIsbn(isbn2));
        Assertions.assertTrue(isInCache(isbn2));

        // When
        bookService.remove(isbn2);

        // Then
        Assertions.assertFalse(isInCache(isbn2));
    }
    
    @Test
    public void updateCacheTest() {
        // Given
        final String isbn3 = "isbn3";
        final Book expected = new Book(isbn3, "author33", "title33");
        Assertions.assertFalse(isInCache(isbn3));
        Assertions.assertNotNull(bookService.findByIsbn(isbn3));
        Assertions.assertTrue(isInCache(isbn3));

        // When
        final Book actual = bookService.update(isbn3, expected);

        // Then
        Assertions.assertEquals(expected, actual);
        Assertions.assertTrue(isInCache(isbn3));
    }

    @Test
    public void addCacheTest() {
        // Given
        final String isbn4 = "isbn4";
        Assertions.assertFalse(isInCache(isbn4));

        // When
        Assertions.assertNotNull(bookService.add(isbn4, new Book(isbn4, "author4", "title4")));

        // Then
        Assertions.assertTrue(isInCache(isbn4));
    }

    private boolean isInCache(final String key) {
        final Cache cache = cacheManager.getCache(CACHE_NAME);
        return (cache != null) && cache.containsKey(key);
    }

}