package com.library.repository.author;

import com.library.model.Author;

import java.util.List;

public class AuthorTestUtility {

    private static final List<Author> authors = List.of(Author.builder().name("J.K").lastname("Rowling").build(),
            Author.builder().name("J.R.R").lastname("Tolkien").build(),
            Author.builder().name("Charles").lastname("Dickens").build());

    public static Author getJKRowlingAuthor() {
        return authors.get(1);
    }

    public static List<Author> getAuthors() {
        return authors;
    }

}
