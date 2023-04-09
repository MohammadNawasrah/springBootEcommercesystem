package in.nawasrah.ecommercesystemAPI.service;

import in.nawasrah.ecommercesystemAPI.model.Users;
import in.nawasrah.ecommercesystemAPI.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class UserService {
    @Autowired
    UserRepos userRepos;

    public String insertUser(Users user) {
        String userR = "";
        try {
            userR = userRepos.insertUser(user);
            return userR;
        } catch (SQLException e) {
            return userR;
        }

    }

    public Users findByWhere(String column, Object data) {
        Users userR = null;
        userR = userRepos.findByWhere(column, data);
        return userR;

    }

    public String signin(String email, String password) {
        String ifExistEmail = userRepos.ifExistsEmail(email);
        if (ifExistEmail.equals("Exist Email")) {
            return userRepos.checkPassword(email, password);
        }
        return "Not Exist Email";
    }

    public String updateUser(String column, Object value, String key, String email) {
        return userRepos.updateByEmail(column, value, key, email);
    }
    public String checkVerifyCode(String email, Object verifyCode) {
        return userRepos.checkVerifyCode(email,verifyCode);
    }


}
