package junit.junit.repository;

import junit.junit.model.User;

import java.util.List;
import java.util.Optional;

public interface Repository {
    public User save(User user);

    public List<User> getAll();

    public Optional<User> getById(Long id);

    public void deleteUser(Long id);

    public void updateUser(User user);
}
