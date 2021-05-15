package bikini.potato.email.client;

public interface EmailClient {

    void sendEmail(String emailAddress, String body, boolean asHTML);
}
