package bikini.potato.email.service.impl;

import bikini.potato.email.client.EmailClient;
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
            IndividualEmail individualEmail = createFromEmail(emailAddress, email);
            sendIndividualEmail(individualEmail);
        }
    }


    private void sendIndividualEmail(IndividualEmail individualEmail) {

        String aa = appendDisclaimerForExternalAddressesIfNeeded(individualEmail.getAddress(), individualEmail.getBody());

        String finalBody = processEncryptions(aa, individualEmail.getEncryptions());

        emailClient.sendEmail(individualEmail.getAddress(), finalBody, individualEmail.isAsHTML());//todo try catch blah
    }

    private String processEncryptions(String text, Queue<Encryption> encryptions) {
        String result = text;
        for(Encryption encryption : encryptions) {
            result = encryptionService.encryptWithAlgorithm(result, encryption);
        }
        return result;
    }

    private boolean isEmailAddressExternal(String emailAddress) {
        //just some basic crude guess...
        return !emailAddress.split("@")[1].contains("internal");
    }

    private String appendDisclaimerForExternalAddressesIfNeeded(String emailAddress, String body) {
        if(isEmailAddressExternal(emailAddress)) {
            return body + " " + propertiesService.getPropertyAsString("email.disclaimer");
        } else {
            return body;
        }
    }

    private IndividualEmail createFromEmail(String address, Email email) {
        Integer maxAttempts = propertiesService.getPropertyAsInteger("email.retries");
        return new IndividualEmail(address, email.getBody(), email.isAsHTML(), email.getEncryptions(), maxAttempts);
    }

    private List<String> extractAllAddresses(Email email) {
        List<String> result = new ArrayList<String>();
        result.addAll(email.getTo());
        result.addAll(email.getCc());
        result.addAll(email.getBcc());
        return result;
    }
}
