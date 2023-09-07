package net.app.goodreview.service;

import net.app.goodreview.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findByEmail(String email);

    User findByLogin(String login);

    List<User> getAll();

    User findById(Long id);

    void delete(Long id);

    User update(User user);
}
