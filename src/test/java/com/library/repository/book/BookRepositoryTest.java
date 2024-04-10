package com.library.repository.book;


import com.library.model.Book;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

@DataJpaTest
public class BookRepositoryTest {

    AuthorRepository authorRepository;
    BookRepository bookRepository;

    @Autowired
    public BookRepositoryTest(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Test
    void testBooksIdGenerationAndFields() {
        //Creating and saving books objects
        Set<Book> books = BookTestUtility.getHPBooks();
        List<Book> savedBooks = bookRepository.saveAll(books);
        assertThat(savedBooks).isNotNull();
    }
}
