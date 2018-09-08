package org.lforeman.models.data;

import org.lforeman.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

    List<User> findByUsername (String username);
}
