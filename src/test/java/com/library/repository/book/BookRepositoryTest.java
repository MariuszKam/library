package com.library.repository.book;


import com.library.model.Book;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class BookRepositoryTest {

    private BookRepository bookRepository;
    private Book book = BookTestUtility.getHPBook();
    private Book savedBook;


    @Autowired
    public BookRepositoryTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    void setUp() {
        savedBook = bookRepository.save(book);
    }

    @Test
    void testCreateBook() {
        //Assertion of author with AssertJ
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(savedBook.getPublicationYear()).isEqualTo(book.getPublicationYear());
        assertThat(savedBook.getIsbn()).isEqualTo(book.getIsbn());
    }

    @Test
    void testReadBook() {

    }

    @AfterEach
    void cleanUp() {
        bookRepository.deleteAll();
    }
}
