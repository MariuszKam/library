package com.library.utility;

import com.library.model.Author;

public class AuthorTestUtility {

    public static Author getJKRowlingAuthor() {
        return Author.builder()
                .name("J.K")
                .lastname("Rowling").
                build();
    }

}
