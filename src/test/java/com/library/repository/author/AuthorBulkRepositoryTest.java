package com.library.repository.author;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.model.Author;
import com.library.repository.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class AuthorBulkRepositoryTest {

    private AuthorRepository authorRepository;
    private List<Author> authors = AuthorTestUtility.getAuthors();
    private List<Author> authorsToSave;


    @Autowired
    public AuthorBulkRepositoryTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    void testBulkCreateAuthors() {
        authorsToSave = authorRepository.saveAll(authors);
        //Assert that all authors are saved
        assertThat(authorsToSave).isNotNull();
        assertThat(authorsToSave.size()).isEqualTo(authors.size());
    }

    @Test
    void testBulkReadAuthors() {
        List<Author> authorFromDB = authorRepository.findAll();
        //Asserts that it contains all entities that were saved before to DB
        authorFromDB.forEach(author -> assertThat(authors).contains(author));
    }

    @Test
    void testBulkDeleteAuthors() {
        authorRepository.deleteAll();
        List<Author> authorFromDB = authorRepository.findAll();
        //Assert that all records are deleted from DB
        assertThat(authorFromDB.size()).isEqualTo(0);
    }

    @AfterEach
    void cleanUp() {
        //Clean up the test data after each test
        authorRepository.deleteAll();
    }


}
