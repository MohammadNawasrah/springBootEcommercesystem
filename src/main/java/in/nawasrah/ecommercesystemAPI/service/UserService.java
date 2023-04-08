package in.nawasrah.ecommercesystemAPI.service;

import in.nawasrah.ecommercesystemAPI.model.User;
import in.nawasrah.ecommercesystemAPI.repository.UserRepos;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService extends UserRepos {
    public List<User> getAllUsers() {
        return findAll();
    }

    public User getUserById(long id) {
        return findById(id);
    }

    public String saveUser(User user) {
        String isAlreadyExist = ifExistsUser(user.getUsers_email());
        if (isAlreadyExist.equals("not Exist")) {
            if (insert(user))
                return "Done Save";
            else
                return "error";
        }
        return isAlreadyExist;
    }

    public String updateEmployee(User user, long id) {
        if (updateById(user, id))
            return "Done update";
        else
            return "error";
    }

    public String signin(String email, String password) {
        String ifExistEmail = ifExistsUser(email);
        if (!ifExistEmail.equals("not Exist")) {
            return checkPassword(email, password);
        }
        return ifExistEmail;
    }
}
