package in.nawasrah.ecommercesystemAPI.service;

import in.nawasrah.ecommercesystemAPI.model.User;
import in.nawasrah.ecommercesystemAPI.repository.UserRepos;
import org.springframework.stereotype.Service;


@Service
public class UserService extends UserRepos {
//    public List<Employee> getAllEmployee() {
//        return findAll();
//    }

    public User getUserById(long id) {
        return findById(id);
    }

    public String saveUser(User user) {
        String isAlreadyExist=ifExistsUser(user);
        if (isAlreadyExist.equals("Done save")) {
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

//    public String deleteEmployee(long id) {
//        if (remove(id))
//            return "Done remove";
//        else
//            return "error";
//    }
}
