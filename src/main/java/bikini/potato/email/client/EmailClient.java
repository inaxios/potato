package bikini.potato.email.client;

import bikini.potato.email.client.exception.EmailClientException;

public interface EmailClient {

    void sendEmail(String emailAddress, String subject, String body, boolean asHTML, Integer attemptNumber) throws EmailClientException;
}
