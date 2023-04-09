package in.nawasrah.ecommercesystemAPI.controller;

import in.nawasrah.ecommercesystemAPI.core.email.Email;
import in.nawasrah.ecommercesystemAPI.model.Users;
import in.nawasrah.ecommercesystemAPI.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;


@RequestMapping(value = "/users")

@RestController
public class UsersController {
    @Autowired
    UserService userService;
    @Autowired
    Email gmail;

    @GetMapping("/send-email")
    public String sendEmail(@RequestBody Map<String, String> sentEmail) {
        try {
            for (int i = 1; i <= 10; i++) {
                gmail.sendEmail(sentEmail.get("toEmail"), sentEmail.get("subject"), "i love you " + i + 99);
                System.out.println("send " + i);
            }
            return "Email sent successfully.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestBody Users users) throws Exception {
        return userService.insertUser(users);
    }

    @PostMapping("/where")
    public Users userId(@RequestBody Map<String, Object> id) throws Exception {
        System.out.println(id.get("where").toString() + id.get("id").getClass());
        return userService.findByWhere(id.get("where").toString(), id.get("id"));
    }

    @PostMapping("/signin")
    public String signin(@RequestBody Map data, HttpServletResponse httpResponse) throws IOException {
        String response = userService.signin(data.get("email").toString(), data.get("password").toString());
        return response.equals("Done Save") ? "error in password or email" : "correct login";
    }
}