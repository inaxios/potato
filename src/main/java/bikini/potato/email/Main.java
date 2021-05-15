package bikini.potato.email;

import bikini.potato.email.model.Email;
import bikini.potato.email.service.EmailService;
import bikini.potato.email.service.impl.EmailServiceImpl;

public class Main {

    public static void main(String[] args) {

        EmailService emailService = new EmailServiceImpl();

        emailService.send(getEmailOne());
        emailService.send(getEmailTwo());
        emailService.send(getEmailThree());
        emailService.send(getEmailFour());
    }

    private static Email getEmailOne() {
        Email email = new Email();

        email.addTO("manolo@internal.com");
        email.addBCC("txema@external.com");
        email.setBody("Egunon bizkaia!");

        return email;
    }

    private static Email getEmailTwo() {
        Email email = new Email();

        email.addTO("manolo@internal.com");
        email.addBCC("txema@external.com");
        email.setAsHTML(true);
        email.setEncryptedWithAES(true);
        email.setBody("Egunon bizkaia!");

        return email;
    }

    private static Email getEmailThree() {
        Email email = new Email();

        email.addTO("manolo@internal.com");
        email.addBCC("txema@external.com");
        email.setAsHTML(true);
        email.setEncryptedWithDES(true);
        email.setBody("Egunon bizkaia!");

        return email;
    }

    private static Email getEmailFour() {
        Email email = new Email();

        email.addTO("manolo@external.com");
        email.addBCC("txema@external.com");
        email.setEncryptedWithDES(true);
        email.setEncryptedWithAES(true);
        email.setBody("Egunon bizkaia!");

        return email;
    }
}
