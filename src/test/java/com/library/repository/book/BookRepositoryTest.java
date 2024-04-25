package com.library.repository.book;


import com.library.model.Author;
import com.library.model.Book;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.repository.author.AuthorTestUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class BookRepositoryTest {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private Book book = BookTestUtility.getHPBook();
    private Book savedBook;


    @Autowired
    public BookRepositoryTest(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @BeforeEach
    void setUp() {
        Author jkr = AuthorTestUtility.getJKRowlingAuthor();
        jkr = authorRepository.save(jkr);
        book.setAuthor(jkr);
        savedBook = bookRepository.save(book);
    }

    @Test
    public void testCreateBook() {
        //Assertion of author with AssertJ
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(savedBook.getPublicationYear()).isEqualTo(book.getPublicationYear());
        assertThat(savedBook.getIsbn()).isEqualTo(book.getIsbn());
    }

    @Test
    public void testReadBook() {
        Book retrievedBook = bookRepository.findById(savedBook.getId()).orElse(null);

        //Assert that the retrieved book exists
        assertThat(retrievedBook).isNotNull();

        // Assert that the retrieved book's attributes match the saved book's attributes
        assertThat(retrievedBook.getId()).isEqualTo(savedBook.getId());
        assertThat(retrievedBook.getTitle()).isEqualTo(savedBook.getTitle());
        assertThat(retrievedBook.getAuthor()).isEqualTo(savedBook.getAuthor());
        assertThat(retrievedBook.getPublicationYear()).isEqualTo(savedBook.getPublicationYear());
        assertThat(retrievedBook.getIsbn()).isEqualTo(savedBook.getIsbn());
    }

    @Test
    public void testUpdateBook() {
        book.setTitle("Renamed");
        savedBook = bookRepository.save(book);
        //Assertion update
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Renamed");
    }

    @Test
    public void testDeleteBook() {
        bookRepository.delete(book);
        Book retrieveBook = bookRepository.findById(savedBook.getId()).orElse(null);
        //Assert if it's deleted/empty
        assertThat(retrieveBook).isNull();
    }

    @AfterEach
    void cleanUp() {
        bookRepository.deleteAll();
    }
}
