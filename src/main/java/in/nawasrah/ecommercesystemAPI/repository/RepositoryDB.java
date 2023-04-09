package in.nawasrah.ecommercesystemAPI.repository;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface RepositoryDB<T> {
    List<T> findAll();

    T findByWhere(String column, Object data);

    String ifExistsEmail(String data);

    String insertUser(T data) throws SQLException;

    String checkPassword(String email, String password);
}
