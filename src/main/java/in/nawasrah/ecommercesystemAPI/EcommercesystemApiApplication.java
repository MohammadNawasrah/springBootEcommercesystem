package in.nawasrah.ecommercesystemAPI;

import com.fasterxml.jackson.core.JsonToken;
import in.nawasrah.ecommercesystemAPI.core.email.Gmail;
import in.nawasrah.ecommercesystemAPI.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EcommercesystemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommercesystemApiApplication.class, args);
    }

}
