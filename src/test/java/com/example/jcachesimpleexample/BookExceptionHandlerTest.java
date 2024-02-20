package com.example.jcachesimpleexample;

import com.example.jcachesimpleexample.BookController;
import com.example.jcachesimpleexample.BookService;
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

@WebMvcTest(BookController.class)
public class BookExceptionHandlerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    BookService bookService;

    @Test
    public void testGlobalExceptionHandlerError() throws Exception {

        Mockito.when(this.bookService.findByIsbn("isbn1"))
                .thenThrow(new IllegalArgumentException("No book found for isbn: isbn1"));

        mvc.perform(MockMvcRequestBuilders
                .get("/books/isbn1")
                .accept(MediaType.ALL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("No book found for isbn: isbn1"));
    }

}