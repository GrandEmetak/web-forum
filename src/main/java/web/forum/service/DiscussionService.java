package web.forum.service;

import org.springframework.stereotype.Service;
import web.forum.model.Discussion;
import web.forum.model.User;
import web.forum.repository.DiscussionRepository;

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
