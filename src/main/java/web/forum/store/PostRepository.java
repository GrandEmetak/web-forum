package web.forum.store;

import org.springframework.data.repository.CrudRepository;
import web.forum.model.Post;


public interface PostRepository extends CrudRepository<Post, Long> {
}
