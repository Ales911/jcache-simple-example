package com.example.jcachesimpleexample;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest({BookController.class})
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    BookService bookService;

    private final List<Book> books = List.of(
            new Book("isbn1", "author1", "title1"),
            new Book("isbn2", "author2", "title2"),
            new Book("isbn3", "author3", "title3"));

    @Test
    public void findAllShouldReturnAllBooks() throws Exception {
        Mockito.when(this.bookService.findAll()).thenReturn(books);

        mvc.perform(MockMvcRequestBuilders
                .get("/books")
                .accept(MediaType.ALL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                // ;charset=UTF-8
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].isbn").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].author").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].title").isNotEmpty());
    }

    @Test
    public void findOneShouldReturnValidBook() throws Exception {
        Mockito.when(this.bookService.findByIsbn("isbn1")).thenReturn(books.get(0));

        mvc.perform(MockMvcRequestBuilders
                .get("/books/isbn1")
                .accept(MediaType.ALL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("isbn1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("author1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title1"));
    }

    @Test
    public void createOneShouldReturnValidBook() throws Exception {
        Mockito.when(this.bookService.add("isbn1", books.get(0))).thenReturn(books.get(0));

        mvc.perform(MockMvcRequestBuilders.post("/books")
                .content(bookAsJson(books.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("isbn1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("author1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title1"));
    }
    
    @Test
    public void updateOneShouldReturnValidBook() throws Exception {
        Mockito.when(this.bookService.update("isbn1", books.get(0))).thenReturn(books.get(0));

        mvc.perform(MockMvcRequestBuilders.put("/books/isbn1")
                .content(bookAsJson(books.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("isbn1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("author1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title1"));
    }
    
    @Test
    public void deleteOneShouldReturnNoContent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/books/isbn1")
                .accept(MediaType.ALL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        
        Mockito.verify(bookService, Mockito.times(1)).remove("isbn1");
    }

    private String bookAsJson(Book book) {
        return String.format("{\"isbn\":\"%s\",\"author\":\"%s\",\"title\":\"%s\"}", book.isbn(), book.author(), book.title());
    }

}