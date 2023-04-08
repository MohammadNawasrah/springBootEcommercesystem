package in.nawasrah.ecommercesystemAPI.controller;

import com.fasterxml.jackson.databind.JsonNode;
import in.nawasrah.ecommercesystemAPI.model.User;
import in.nawasrah.ecommercesystemAPI.repository.UserRepos;
import in.nawasrah.ecommercesystemAPI.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Types;
import java.util.List;
import java.util.Map;


@RequestMapping(value = "/users")

@RestController
public class UsersController {
    @Autowired
    UserService userService;

    //@GetMapping("")
//    public String createDB(){
////    userRepos.connection();
//    return "done";
//}
    @GetMapping("")
    public List<User> displayEmployees(HttpServletResponse httpResponse) throws IOException {
        List<User> employees = userService.getAllUsers();
        if (employees != null) {
            return employees;
        } else {
            httpResponse.sendRedirect("error");
            return null;
        }
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    //
    @GetMapping("/user/{id}")
    public User displayUser(@PathVariable("id") Long id, HttpServletResponse httpResponse) throws IOException {
        User user = userService.getUserById(id);
        if (user != null) {
            return user;
        } else {
            httpResponse.sendRedirect("error");
            return null;
        }
    }

    //
    @PostMapping("/signup")
    public String signup(@RequestBody User user, HttpServletResponse httpResponse) throws IOException {
        String response = userService.saveUser(user);
        return response;
    }

    @PostMapping("/signin")
    public String signin(@RequestBody Map  data, HttpServletResponse httpResponse) throws IOException {
        String response = userService.signin(data.get("email").toString(), data.get("password").toString());
        return response;
    }

    @PutMapping("/user/{id}")
    public String updateEmployee(@PathVariable("id") long id, @RequestBody User employee, HttpServletResponse httpResponse) throws IOException {
        String e = userService.updateEmployee(employee, id);
        return e;
    }
//    @DeleteMapping("/employee")
//    public String deleteEmployee(@RequestParam("id") long id,HttpServletResponse httpResponse) throws IOException {
//        String e = employeeService.deleteEmployee(id);
//        return e;
//    }
}