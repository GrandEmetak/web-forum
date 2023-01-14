package web.forum.repository;

import org.springframework.data.repository.CrudRepository;

import web.forum.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
