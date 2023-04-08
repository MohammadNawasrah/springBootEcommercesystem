package in.nawasrah.ecommercesystemAPI.repository;

import in.nawasrah.ecommercesystemAPI.database.DbSql;
import in.nawasrah.ecommercesystemAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepos implements RepositoryDB<User> {
    private String stringConnection = "jdbc:sqlite:C:\\Users\\nawas\\Desktop\\springBoot\\ecommercesystemAPI\\src\\main\\java\\in\\nawasrah\\ecommercesystemAPI\\database\\data\\employee.db";
    @Autowired
    private DbSql dbSql;
    private Connection con;

    User setUser(User user, ResultSet employees) {
        try {
            user.setUsers_id(employees.getLong("users_id"));
            user.setUsers_name(employees.getString("users_name"));
            user.setUsers_email(employees.getString("users_email"));
            user.setUser_create(employees.getDate("user_create"));
            user.setUsers_verifycode(employees.getInt("users_verifycode"));
            user.setUsers_approve(employees.getBoolean("users_approve"));
            return user;
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }

    public Connection connection() {

        con = dbSql.connection(this.stringConnection);
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "  users_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "  users_name TEXT NOT NULL," +
                "  users_email TEXT NOT NULL," +
                "  users_verifycode INTEGER," +
                " user_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                " users_approve BOOLEAN DEFAULT 0" +
                ")";
        dbSql.createTable(sql, con);
        return con;
    }

//    Employee setEmployee(Employee e, ResultSet employees) {
//        try {
//            e.setId(employees.getLong("id"));
//            e.setName(employees.getString("name"));
//            e.setAge(employees.getInt("age"));
//            e.setLocation(employees.getString("location"));
//            e.setEmail(employees.getString("email"));
//            e.setDepartment(employees.getString("department"));
//            return e;
//        } catch (Exception exception) {
//            return null;
//        }
//    }

    @Override
    public List<User> findAll() {
        try {
            String sql = "SELECT * FROM users";
            if (connection() != null) {
                ResultSet user = dbSql.select(sql, connection());
                List<User> userList = new ArrayList<>();
                while (user.next()) {
                    User e = new User();
                    setUser(e, user);
                    userList.add(e);
                }
                return userList;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User findById(long id) {
        try {
            String sql = "SELECT * FROM users WHERE users_id=" + id + "";
            int count = 0;
            if (connection() != null) {
                ResultSet users = dbSql.select(sql, connection());
                User user = new User();
                while (users.next()) {
                    count++;
                    user = setUser(user, users);
                }

                if (count > 0)
                    return user;
                else
                    return null;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateById(User user, long id) {
        try {
            String sql = "UPDATE users SET users_name ='%s',users_email='%s' , users_verifycode =%d, users_approve =%b WHERE users_id=%d";
            String sqlF = String.format(sql, user.getUsers_name(), user.getUsers_email(), user.getUsers_verifycode(), user.isUsers_approve()
                    , id);
            if (connection() != null) {
                boolean ifUpdate = dbSql.update(sqlF, connection());
                return ifUpdate;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String ifExistsUser(User user) {
        List<User> allUsers=new ArrayList<>();
        allUsers= findAll();
        for (User users : allUsers) {
            if (users.getUsers_email().equals(user.getUsers_email()))
                return "Your Email is already exist";
        }
        return "Done save";
    }

//    @Override
//    public boolean remove(long id) {
//        try {
//            String sql = "DELETE FROM employees WHERE id=" + id + "";
//            if (connection() != null) {
//                boolean ifDelete = dbSql.delete(sql, connection());
//                return ifDelete;
//            }
//            return false;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }


    @Override
    public boolean insert(User user) {
        try {
            String sql = "INSERT INTO users (users_name,users_email,users_verifycode)VALUES(\"%s\",\"%s\",%d)";
            String sqlF = String.format(sql, user.getUsers_name(), user.getUsers_email(), user.getUsers_verifycode()
                    , user.getUsers_verifycode());
            if (connection() != null) {
                boolean ifInsert = dbSql.insert(sqlF, connection());
                return ifInsert;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
