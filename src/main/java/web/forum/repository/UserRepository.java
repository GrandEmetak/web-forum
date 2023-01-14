package web.forum.repository;

import org.springframework.data.repository.CrudRepository;

import web.forum.model.User;

public interface UserRepository  extends CrudRepository<User, Integer> {

    User findUserByUsername(String userName);
}
