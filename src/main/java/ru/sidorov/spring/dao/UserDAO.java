package ru.sidorov.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sidorov.spring.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User showUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Transactional
    public void update(User updateUser) {
        entityManager.merge(updateUser);
        entityManager.flush();
    }

    @Transactional
    public void delete(Long id) {
        entityManager.remove(showUser(id));
        entityManager.flush();
    }
}
