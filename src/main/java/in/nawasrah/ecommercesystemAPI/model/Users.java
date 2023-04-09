package in.nawasrah.ecommercesystemAPI.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Users {
    private long users_id;
    private String users_name;
    private String users_password;
    private String users_email;
    private int users_verifycode;
    private boolean users_approve;
    private Date user_create;
}
