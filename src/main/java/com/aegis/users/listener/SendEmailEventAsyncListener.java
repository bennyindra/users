package com.aegis.users.listener;

import com.aegis.users.event.SendEmailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@SuppressWarnings("unused")
public class SendEmailEventAsyncListener {

    @Value("${mail.admin:benny.noreply@gmail.com}")
    private String toAdmin;
    @Value("${spring.mail.username}")
    private String emailFrom;
    @Autowired
    private JavaMailSender javaMailSender;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void processCustomerCreatedEvent(SendEmailEvent event) {
        SimpleMailMessage userEmail =  constructEmail(emailFrom, event.getUser().getEmail(),
                "Your User at AEGIS Created",
                String.format("Your user '%s' at aegis has successfully created", event.getUser().getName()));
        SimpleMailMessage adminEmail =  constructEmail(emailFrom, toAdmin,
                String.format("Notification - User with name : '%s' is created", event.getUser().getName()),
                String.format("A user has created with name : '%s' and email: %s", event.getUser().getName(), event.getUser().getName()));
        javaMailSender.send(userEmail);
        javaMailSender.send(adminEmail);
    }

    private static SimpleMailMessage constructEmail(String from, String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        return simpleMailMessage;
    }

}
