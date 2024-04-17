package com.library.repository.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.model.User;
import com.library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        User user = UserTestUtility.getTestUser();
        userRepository.save(user);
        Optional<User> optionalUserFromDb = userRepository.findByUsername(user.getUsername());
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
