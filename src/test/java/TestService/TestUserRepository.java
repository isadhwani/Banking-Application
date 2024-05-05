package TestService;

import dev.codescreen.models.repositories.User;
import dev.codescreen.models.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserRepository {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void testUserSavedAndExistsById() {
        UserRepository ur = new UserRepository();
        ur.save(new User("1", 100));

        assert (ur.existsById("1"));
    }

    @Test
    public void testSave() {
        User user = new User("1", 100);
        userRepository.save(user);
        assertTrue(userRepository.existsById("1"));
    }

    @Test
    public void testSaveAll() {
        User user1 = new User("1", 100);
        User user2 = new User("2", 200);
        userRepository.saveAll(Arrays.asList(user1, user2));
        assertTrue(userRepository.existsById("1"));
        assertTrue(userRepository.existsById("2"));
    }

    @Test
    public void testFindById() {
        User user = new User("1", 100);
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findById("1");
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    public void testExistsById() {
        User user = new User("1", 100);
        userRepository.save(user);
        System.out.println(userRepository.count());

        assertTrue(userRepository.existsById("1"));
        assertFalse(userRepository.existsById("2"));
    }

    @Test
    public void testFindAll() {
        User user1 = new User("1", 100);
        User user2 = new User("2", 200);
        userRepository.saveAll(Arrays.asList(user1, user2));
        Iterable<User> users = userRepository.findAll();
        assertTrue(users.iterator().hasNext());
    }

    @Test
    public void testCount() {
        User user1 = new User("1", 100);
        User user2 = new User("2", 200);
        userRepository.saveAll(Arrays.asList(user1, user2));
        assertEquals(2, userRepository.count());
    }

    @Test
    public void testDeleteById() {
        User user = new User("1", 100);
        userRepository.save(user);
        userRepository.deleteById("1");
        assertFalse(userRepository.existsById("1"));
    }

    @Test
    public void testDelete() {
        User user = new User("1", 100);
        userRepository.save(user);
        userRepository.delete(user);

        System.out.println(userRepository.count());

        assertFalse(userRepository.existsById("1"));
    }

    @Test
    public void testDeleteAllById() {
        User user1 = new User("1", 100);
        User user2 = new User("2", 200);
        userRepository.saveAll(Arrays.asList(user1, user2));
        userRepository.deleteAllById(Arrays.asList("1", "2"));
        assertFalse(userRepository.existsById("1"));
        assertFalse(userRepository.existsById("2"));
    }

    @Test
    public void testDeleteAll() {
        User user1 = new User("1", 100);
        User user2 = new User("2", 200);
        userRepository.saveAll(Arrays.asList(user1, user2));
        userRepository.deleteAll();
        assertEquals(0, userRepository.count());
    }


}
