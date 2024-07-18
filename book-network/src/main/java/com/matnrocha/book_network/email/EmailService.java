package com.matnrocha.book_network.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    /**
     * Send emails using thymeleaf through context and template
     * @param to
     * @param username
     * @param subject
     * @param emailTemplate
     * @param confirmationUrl
     * @param activationCode
     * @throws MessagingException
     */
    @Async
    public void sendEmail(
            String to,
            String username,
            String subject,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode
    ) throws MessagingException
    {
        String templateName;
        if(emailTemplate == null){
            templateName = "confirm-email";
        } else{
            templateName = emailTemplate.getName();
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );

        //Thymeleaf context properties
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activation_code", activationCode);

        Context context = new Context();
        context.setVariables(properties);

        //
        helper.setFrom("mateusanroc@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        //Look for the {templateName}.html in templates folder passing the context
        String template = templateEngine.process(templateName, context);
        helper.setText(template, true);

        mailSender.send(mimeMessage);
    }
}
