package by.lyofa.jcachesimpleexample;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

public record Book(
        @NotNull(message = "Isbn not be null")
        @NotBlank(message = "Invalid isbn: Empty isbn")
        @Size(min = 3, max = 30, message = "Invalid isbn: Exceeds 30 characters")
        String isbn,
        @NotNull(message = "Author not be null")
        @NotBlank(message = "Invalid author: Empty author")
        @Size(min = 1, max = 30, message = "Invalid author: Exceeds 30 characters")
        String author,
        @NotNull(message = "Title not be null")
        @NotBlank(message = "Invalid title: Empty title")
        @Size(min = 1, max = 30, message = "Invalid title: Exceeds 30 characters")
        String title) implements Serializable {

}
