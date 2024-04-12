package com.library.repository.author;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.model.Author;
import com.library.repository.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class AuthorBulkRepositoryTest {

    private AuthorRepository authorRepository;
    private List<Author> authors;
    private List<Author> authorsToSave;

    @Autowired
    public AuthorBulkRepositoryTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @BeforeEach
    void setUp() {
        //Load list of authors
        authors = AuthorTestUtility.getAuthors();
        //Save it to repository
        authorsToSave = authorRepository.saveAll(authors);
    }

    @Test
    void testBulkCreateAuthors() {
        //Assert that all authors are saved
        assertThat(authorsToSave).isNotNull();
        assertThat(authorsToSave.size()).isEqualTo(authors.size());
    }

    @Test
    void testBulkReadAuthors() {
        List<Author> authorsFromDB = authorRepository.findAll();
        //Asserts that it contains all entities that were saved before to DB
        assertThat(authorsFromDB).isNotEmpty();
        authors.forEach(author -> assertThat(authorsFromDB).contains(author));
    }

    @Test
    void testBulkDeleteAuthors() {
        authorRepository.deleteAll();
        List<Author> authorsFromDB = authorRepository.findAll();
        System.out.println(authorsFromDB);
        //Assert that all records are deleted from DB
        assertThat(authorsFromDB.size()).isEqualTo(0);
    }

    @AfterEach
    void cleanUp() {
        //Clean up the repository after each test
        authorRepository.deleteAll();
    }

}
