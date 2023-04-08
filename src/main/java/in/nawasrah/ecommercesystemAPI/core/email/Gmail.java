package in.nawasrah.ecommercesystemAPI.core.email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class Gmail implements Email {
    @Autowired
    private JavaMailSender javaMailSender;

    public String sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);

//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(toEmail);
//            helper.setSubject(subject);
//            helper.setText(body);
//
//            // Attach the file
//            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
//            helper.addAttachment(file.getFilename(), file);

            javaMailSender.send(message);
            return "Done Email send";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Email don's sent";
        }


    }

}
