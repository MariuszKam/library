package com.library.repository.author;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.model.Author;
import com.library.repository.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AuthorRepositoryTest {

    private AuthorRepository authorRepository;
    private Author author = AuthorTestUtility.getJKRowlingAuthor();
    private Author savedAuthor;

    @Autowired
    public AuthorRepositoryTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @BeforeEach
    void setUp() {
        //Creating and saving author object
        savedAuthor = authorRepository.save(author);
    }

    @Test
    void testCreateAuthor() {
        //Assertion of author with AssertJ
        assertThat(savedAuthor.getId()).isNotNull();
        assertThat(savedAuthor.getName()).isEqualTo(author.getName());
        assertThat(savedAuthor.getLastname()).isEqualTo(author.getLastname());
    }

    @Test
    void testReadAuthor() {
        Author retrieveAuthor = authorRepository.findById(savedAuthor.getId()).orElse(null);

        //Assert that the author is present
        assertThat(retrieveAuthor).isNotNull();

        //Assert if data is correct
        assertThat(retrieveAuthor.getId()).isEqualTo(savedAuthor.getId());
        assertThat(retrieveAuthor.getName()).isEqualTo(savedAuthor.getName());
        assertThat(retrieveAuthor.getLastname()).isEqualTo(savedAuthor.getLastname());

    }

    @Test
    void testUpdateAuthor() {
        author.setName("Renamed");
        savedAuthor = authorRepository.save(author);
        //Assertion update
        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getName()).isEqualTo("Renamed");
    }

    @Test
    void testDeleteAuthor() {
        authorRepository.delete(author);
        Author retrieveAuthor = authorRepository.findById(savedAuthor.getId()).orElse(null);
        //Assert if it's deleted/empty
        assertThat(retrieveAuthor).isNull();
    }

    @AfterEach
    void cleanUp() {
        //Clean up the test data after each test
        authorRepository.deleteAll();
    }

}
