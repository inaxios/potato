package bikini.potato.email.client.impl;

import bikini.potato.email.client.EmailClient;

public class EmailClientImpl implements EmailClient {

    @Override
    public void sendEmail(String emailAddress, String body, boolean asHTML) {
        if(asHTML) {
            System.out.println("Sending email as HTML to " + emailAddress);
        } else {
            System.out.println("Sending email as text to " + emailAddress);
        }
        System.out.println("\t\t" + body + "\n\n");
    }
}
