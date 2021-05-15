package bikini.potato.email.service.impl;

import bikini.potato.email.client.EmailClient;
import bikini.potato.email.client.exception.EmailClientException;
import bikini.potato.email.client.impl.EmailClientImpl;
import bikini.potato.email.model.Email;
import bikini.potato.email.model.Encryption;
import bikini.potato.email.model.IndividualEmail;
import bikini.potato.email.service.EmailService;
import bikini.potato.email.service.EncryptionService;
import bikini.potato.email.service.PropertiesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class EmailServiceImpl implements EmailService {

    private EmailClient emailClient = new EmailClientImpl();
    private EncryptionService encryptionService = new EncryptionServiceImpl();
    private PropertiesService propertiesService = new PropertiesServiceImpl();

    @Override
    public void send(Email email) {
        List<String> allEmailAddresses = extractAllAddresses(email);
        for(String emailAddress : allEmailAddresses) {
            IndividualEmail individualEmail = createIndividualEmail(emailAddress, email);
            sendIndividualEmail(individualEmail);
        }
    }

    private void sendIndividualEmail(IndividualEmail individualEmail) {

        String intermediateBody = appendDisclaimerForExternalAddressesIfNeeded(individualEmail.getAddress(), individualEmail.getBody());
        String finalBody = processEncryptions(intermediateBody, individualEmail.getEncryptions());

        try {
            if(individualEmail.getAttemptsLeft() > 0) {
                emailClient.sendEmail(individualEmail.getAddress(), individualEmail.getSubject(), finalBody, individualEmail.isAsHTML(), individualEmail.getAttemptsLeft());
            } else {
                System.out.println("Logging this failure for statistics, analysis, finger pointing etc");
            }
        } catch(EmailClientException ex) {
            individualEmail.decreaseAttemptCount();
            System.out.println("Sending email to " + individualEmail.getAddress() + " failed!");
            System.out.println("\treason: " + ex.getMessage());
            System.out.println("\tattempts left: " + individualEmail.getAttemptsLeft());
            sendIndividualEmail(individualEmail);
        }
    }

    private String processEncryptions(String text, Queue<Encryption> encryptions) {
        String result = text;
        for(Encryption encryption : encryptions) {
            result = encryptionService.encryptWithAlgorithm(result, encryption);
        }
        return result;
    }

    private boolean isEmailAddressExternal(String emailAddress) {
        //just some basic, crude guess...
        return !emailAddress.split("@")[1].contains("internal");
    }

    private String appendDisclaimerForExternalAddressesIfNeeded(String emailAddress, String body) {
        if(isEmailAddressExternal(emailAddress)) {
            return body + " " + propertiesService.getPropertyAsString("email.disclaimer");
        } else {
            return body;
        }
    }

    private IndividualEmail createIndividualEmail(String address, Email email) {
        Integer maxAttempts = propertiesService.getPropertyAsInteger("email.retries");
        return new IndividualEmail(address, email.getSubject(), email.getBody(), email.isAsHTML(), email.getEncryptions(), maxAttempts);
    }

    private List<String> extractAllAddresses(Email email) {
        List<String> result = new ArrayList<String>();
        result.addAll(email.getTo());
        result.addAll(email.getCc());
        result.addAll(email.getBcc());
        return result;
    }
}
