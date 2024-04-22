package com.library.repository.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.model.User;
import com.library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cglib.core.internal.Function;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {
    UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void shouldFindUserByUsernameWithoutRoles() {
        //Adding instance of User to db
        User user = UserTestUtility.getTestUser();
        userRepository.save(user);

        assertUserExists(user,user.getUsername(), userRepository::findByUsername);
    }

    @Test
    void shouldFindUserByEmailWithoutRoles() {
        //Adding instance of User to db
        User user = UserTestUtility.getTestUser();
        userRepository.save(user);

        assertUserExists(user, user.getEmail(), userRepository::findByEmail);
    }

    //Utilities
    private void assertUserExists(User user, String identifier, Function<String, Optional<User>> findByFunction) {
        Optional<User> optionalUserFromDb = findByFunction.apply(identifier);
        assertThat(optionalUserFromDb)
                .isPresent()
                .hasValueSatisfying(userFromDb -> {
                    assertThat(userFromDb.getUsername()).isEqualTo(user.getUsername());
                    assertThat(userFromDb.getEmail()).isEqualTo(user.getEmail());
                    assertThat(userFromDb.getPassword()).isEqualTo(user.getPassword());
                    assertThat(userFromDb.getRoles()).isNull();
                });
    }
}
