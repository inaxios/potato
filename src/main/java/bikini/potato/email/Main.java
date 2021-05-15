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
        //sending a plain text email to an outside resource, with a disclaimer added at the end,
        //unencrypted and no retry
        Email email = new Email();
        email.addTO("harry.potter@external.com");
        email.setBody("blah blah blah");

        return email;
    }

    private static Email getEmailTwo() {
        //sending an HTML email to an internal server (so without the disclaimer), encrypted with
        //DES, with the retry functionality
        Email email = new Email();
        email.addTO("spongebob.squarepants@internal.com");
        email.setAsHTML(true);
        email.setEncryptedWithDES(true);
        email.setBody("Egunon bizkaia!");//todo retry

        return email;
    }

    private static Email getEmailThree() {
        //sending an HTML email to an outside resource, with a disclaimer added at the end and
        //encrypted with AES with retries in case of errors
        Email email = new Email();
        email.addTO("manolo@internal.com");
        email.addBCC("txema@external.com");
        email.setAsHTML(true);
        email.setEncryptedWithAES(true);
        email.setBody("Egunon bizkaia!");//todo retry

        return email;
    }

    private static Email getEmailFour() {
        // sending a plain text email to an outside resource and encrypted first with DES and then
        // with AES//todo encrypt order matters
        Email email = new Email();

        email.addTO("manolo@external.com");
        email.addBCC("txema@external.com");
        email.setEncryptedWithDES(true);
        email.setEncryptedWithAES(true);
        email.setBody("Egunon bizkaia!");//todo retry

        return email;
    }
}
