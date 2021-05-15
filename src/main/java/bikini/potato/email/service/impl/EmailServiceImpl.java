package bikini.potato.email.service.impl;

import bikini.potato.email.client.EmailClient;
import bikini.potato.email.client.impl.EmailClientImpl;
import bikini.potato.email.model.Email;
import bikini.potato.email.model.IndividualEmail;
import bikini.potato.email.service.EmailService;
import bikini.potato.email.service.EncryptionService;
import bikini.potato.email.service.PropertiesService;

import java.util.ArrayList;
import java.util.List;

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

        String finalBody = appendDisclaimerForExternalAddressesIfNeeded(individualEmail.getAddress(), individualEmail.getBody());
        if(individualEmail.isEncryptedWithDES()) {
            finalBody = encryptionService.encryptWithDES(finalBody);
        }
        if(individualEmail.isEncryptedWithAES()) {
            finalBody = encryptionService.encryptWithAES(finalBody);
        }

        emailClient.sendEmail(individualEmail.getAddress(), finalBody, individualEmail.isAsHTML());//todo try catch blah
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
        return new IndividualEmail(address, email.getBody(), email.isAsHTML(), email.isEncryptedWithDES(), email.isEncryptedWithAES(), maxAttempts);
    }

    private List<String> extractAllAddresses(Email email) {
        List<String> result = new ArrayList<String>();
        result.addAll(email.getTo());
        result.addAll(email.getCc());
        result.addAll(email.getBcc());
        return result;
    }
}
