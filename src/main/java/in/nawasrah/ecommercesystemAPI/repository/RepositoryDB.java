package in.nawasrah.ecommercesystemAPI.repository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryDB<T> {
    List<T> findAll();
    T findById(long id);
    boolean updateById(T data, long id);
    String ifExistsUser(T data);
//    boolean remove(long id);
    boolean insert(T data);
}
