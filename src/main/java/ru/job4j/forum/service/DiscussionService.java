package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Discussion;
import ru.job4j.forum.repository.DiscussionRepository;

@Service
public class DiscussionService {

    private DiscussionRepository discussionRepository;

    public DiscussionService(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    public Discussion save(Discussion discussion) {
        return discussionRepository.save(discussion);
    }

    public Discussion findById(int id) {
        return discussionRepository.findById(id).orElse(new Discussion());
    }
}
