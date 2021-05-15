package bikini.potato.email.client.impl;

import bikini.potato.email.client.EmailClient;
import bikini.potato.email.client.exception.EmailClientException;

public class EmailClientImpl implements EmailClient {

    @Override
    public void sendEmail(String emailAddress, String subject, String body, boolean asHTML, Integer attemptNumber) throws EmailClientException {
        throwExceptionIfWeHaveTo(emailAddress, attemptNumber);
        if(asHTML) {
            System.out.println("\nEmail client sending email as HTML to " + emailAddress);
        } else {
            System.out.println("\nEmail client sending email as plain text to " + emailAddress);
        }
        System.out.println("\t\tSubject\t: " + subject);
        System.out.println("\t\tBody\t: " + body + "\n\n");
    }

    private void throwExceptionIfWeHaveTo(String emailAddress, Integer attemptNumber) throws EmailClientException {
        if(emailAddress.contains("scarlett") || emailAddress.contains("spongebob")) {
            if(attemptNumber == 3) {
                throw new EmailClientException("Sorry, DG GROW says we have to fail at least once...");
            }
        }
    }
}
