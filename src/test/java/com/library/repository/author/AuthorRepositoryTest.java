package com.library.repository.author;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.model.Author;
import com.library.repository.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

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
    void createAuthor() {
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
        Optional<Author> optionalAuthor = authorRepository.findById(savedAuthor.getId());

        //Assert that the author is present
        assertThat(optionalAuthor).isPresent();

        //Assert if data is correct
        optionalAuthor.ifPresent(a -> {
            assertThat(a.getId()).isEqualTo(savedAuthor.getId());
            assertThat(a.getName()).isEqualTo(savedAuthor.getName());
            assertThat(a.getLastname()).isEqualTo(savedAuthor.getLastname());
        });
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
        Optional<Author> author = authorRepository.findById(1L);
        //Assert if it's deleted/empty
        assertThat(author).isEmpty();
    }

    @AfterEach
    void cleanUp() {
        //Clean up the test data after each test
        authorRepository.deleteAll();
    }

}
