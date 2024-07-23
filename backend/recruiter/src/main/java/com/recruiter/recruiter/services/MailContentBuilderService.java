package com.recruiter.recruiter.services;

import org.springframework.stereotype.Component;

@Component
public class MailContentBuilderService {


        public String buiderContentMail(String username, String token) {
            // Build structure content mail
            return String.format(
                    "<h4>Hi %s,</h4>" +
                            "<p>Please activate your account.</p>" +
                            "<p>Thank you for signing up to TopHire. Please click on the below URL to activate your account:</p>" +
                            "<a href=\"http://localhost:8080/auth/active-account/%s\">Activate Account</a>" +
                            "<p>Thank you for signing up!</p>",
                    username, token
            );
        }
}
