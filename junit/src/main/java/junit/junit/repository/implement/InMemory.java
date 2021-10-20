package junit.junit.repository.implement;

import junit.junit.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class InMemory {

    private static final AtomicLong TS = new AtomicLong();

    private Map<Long, User> users = new HashMap<>();

    //@Override
    public User save(User user) {
        user.setId(1L);
        users.put(1L, user);
        return user;
    }

    //@Override
    public List<User> getAll() {
        return users.entrySet()
                .stream()
                .map(user -> user.getValue())
                .collect(Collectors.toList());
    }

    // @Override
    public Optional<User> getById(Long id) {
        return users
                .entrySet()
                .stream()
                .map(user -> user.getValue())
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    //@Override
    public void deleteUser(Long id) {
        System.out.println("users = " + users.size());
        users.remove(id, getById(id).get());
        System.out.println("users.size() = " + users.size());

    }

    //@Override
    public void updateUser(User user) {

    }

    public static long getUniqueTimestamp() {
        return TS.incrementAndGet();
    }
}
