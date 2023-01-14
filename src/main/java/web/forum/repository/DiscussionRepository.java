package web.forum.repository;

import org.springframework.data.repository.CrudRepository;
import web.forum.model.Discussion;

public interface DiscussionRepository extends CrudRepository<Discussion, Integer> {
}
