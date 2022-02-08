package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Discussion;

public interface DiscussionRepository extends CrudRepository<Discussion, Integer> {
}
