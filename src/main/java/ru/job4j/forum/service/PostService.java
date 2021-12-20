package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

   private PostRepository postRepoditory;

    public PostService(PostRepository postRepoditory) {
        this.postRepoditory = postRepoditory;
    }

    public List<Post> getAll() {
        return postRepoditory.getAll();
    }

}
