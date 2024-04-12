package com.library.repository.author;

import com.library.model.Author;

import java.util.List;

public class AuthorTestUtility {

    public static Author getJKRowlingAuthor() {
        return Author.builder()
                .name("J.K")
                .lastname("Rowling")
                .build();
    }

    public static List<Author> getAuthors() {
        return List.of(Author.builder().name("J.K").lastname("Rowling").build(),
                Author.builder().name("J.R.R").lastname("Tolkien").build(),
                Author.builder().name("Charles").lastname("Dickens").build());
    }

}
