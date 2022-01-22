package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

/**
 * CrudRepository
 *     <S extends T> S save(S var1);
 *
 *     <S extends T> Iterable<S> saveAll(Iterable<S> var1);
 *
 *     Optional<T> findById(ID var1);
 *
 *     boolean existsById(ID var1);
 *
 *     Iterable<T> findAll();
 *
 *     Iterable<T> findAllById(Iterable<ID> var1);
 *
 *     long count();
 *
 *     void deleteById(ID var1);
 *
 *     void delete(T var1);
 *
 *     void deleteAll(Iterable<? extends T> var1);
 *
 *     void deleteAll();
 */
public interface PostRepository extends CrudRepository<Post, Long> {
}
