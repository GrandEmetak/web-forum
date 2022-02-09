package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Discussion;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.DiscussionRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service
public class DiscussionService {

    private DiscussionRepository discussionRepository;

    public DiscussionService(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    public Discussion save(Discussion discussion) {
        discussion.setCreated(Calendar.getInstance());
        return discussionRepository.save(discussion);
    }

    public Discussion findById(int id) {
        return discussionRepository.findById(id).orElse(new Discussion());
    }

    /**
     * Задает принадлежность Поста к определенному пользователю
     * @param discussion
     * @param user
     * @return
     */
    public Discussion putUser(Discussion discussion, User user) {
         discussion.setUser(user);
        return discussion;
    }
}
