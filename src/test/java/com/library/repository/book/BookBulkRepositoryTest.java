package com.library.repository.book;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.model.Author;
import com.library.model.Book;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.repository.author.AuthorTestUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class BookBulkRepositoryTest {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private List<Book> books = BookTestUtility.getHPBooks();
    private List<Book> savedBooks;

    @Autowired
    public BookBulkRepositoryTest(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @BeforeEach
    void setUp() {
        Author jkr = AuthorTestUtility.getJKRowlingAuthor();
        jkr = authorRepository.save(jkr);

        for (Book book : books) {
            book.setAuthor(jkr);
        }
        savedBooks = bookRepository.saveAll(books);
    }

    @Test
    void testBulkCreateBooks() {
        assertThat(savedBooks).isNotNull();
        assertThat(savedBooks.size()).isEqualTo(books.size());
    }

    @Test
    void testBulkReadBooks() {
        List<Book> booksFromDB = bookRepository.findAll();
        assertThat(booksFromDB).isNotNull();
        books.forEach(book -> assertThat(booksFromDB).contains(book));
    }

    @Test
    void testBulkDeleteBooks() {
        bookRepository.deleteAll();
        List<Book> booksFromDB = bookRepository.findAll();
        assertThat(booksFromDB.size()).isEqualTo(0);
    }

    @AfterEach
    void cleanUp() {
        //Clean up database
        bookRepository.deleteAll();
    }
}
