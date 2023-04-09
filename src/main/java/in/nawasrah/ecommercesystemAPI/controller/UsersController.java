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
            gmail.sendEmail(sentEmail.get("toEmail"), sentEmail.get("subject"), "i love you " + Math.round(Math.random() * 500));
            return "Email Send Successfully.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error";
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestBody Users users) throws Exception {
        String response=userService.insertUser(users);
        Long randomVerifyCode=Math.round(10000 + Math.random() * 99999);
        userService.updateUser("users_verifycode",randomVerifyCode, "users_email", users.getUsers_email());
        gmail.sendEmail(users.getUsers_email(),"Ecommercesystem verifyCode","your code is : "+randomVerifyCode);
        return "Done Signup Just Need VerifyCode";
    }

    @PostMapping("/where")
    public Users userId(@RequestBody Map<String, Object> id) throws Exception {
        return userService.findByWhere(id.get("where").toString(), id.get("id"));
    }

    @PostMapping("/signin")
    public String signin(@RequestBody Map data, HttpServletResponse httpResponse) throws IOException {
        String response = userService.signin(data.get("email").toString(), data.get("password").toString());
        return response;
    }

    @PostMapping("/generateVerifyCode")
    public String generateVerifyCode(@RequestBody Map<String, String> email, HttpServletResponse httpResponse) throws IOException {
        return userService.updateUser("users_verifycode", Math.round(10000 + Math.random() * 99999), "users_email", email.get("users_email"));
    }

    @PostMapping("/checkVerifyCode")
    public String checkVerifyCode(@RequestBody Map<String, Object> data, HttpServletResponse httpResponse) throws IOException {
        String response = userService.checkVerifyCode(data.get("users_email").toString(), data.get("users_verifycode"));
        if (response.equals("Correct VerifyCode")) {
            userService.updateUser("users_approve",true, "users_email",  data.get("users_email").toString());
            userService.updateUser("users_verifycode", 0, "users_email", data.get("users_email").toString());
            return response;
        }
        return response;
    }
}