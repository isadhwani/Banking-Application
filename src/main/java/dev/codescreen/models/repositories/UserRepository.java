package dev.codescreen.models.repositories;

import dev.codescreen.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepository implements CrudRepository<User, String> {
    Set<User> users = new HashSet();
    @Override
    public <S extends User> S save(S entity) {
        users.add(entity);
        return entity;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entry : entities) {
            users.add(entry);
        }
        return entities;
    }

    @Override
    public Optional<User> findById(String id) {
        for (User u : users) {
            if(u.getId().equals(id)) {
                return Optional.ofNullable(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return true;
            }
        }
        return true;
    }

    @Override
    public Iterable<User> findAll() {
        return new HashSet<>(users);
    }

    @Override
    public Iterable<User> findAllById(Iterable<String> strss) {
        // todo
        return null;
    }

    @Override
    public long count() {
        return users.size();
    }

    @Override
    public void deleteById(String id) {
        for (User u : users) {
            if(u.getId().equals(id)) {
                users.remove(u);
            }
        }

    }

    @Override
    public void delete(User entity) {
        for (User u : users) {
            if(u.getId().equals(entity)) {
                users.remove(u);
            }
        }

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strs) {
        // todo

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        // todo
    }

    @Override
    public void deleteAll() {
        users = new HashSet<>();
    }
}
