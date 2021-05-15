package bikini.potato.email.service.impl;

import bikini.potato.email.client.EmailClient;
import bikini.potato.email.client.impl.EmailClientImpl;
import bikini.potato.email.model.Email;
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
            sendIndividualEmail(emailAddress, email.getBody(), email.isAsHTML(), email.isEncryptedWithDES(), email.isEncryptedWithAES());
        }
    }


    private void sendIndividualEmail(String emailAddress, String body, boolean asHTML, boolean encryptWithDES, boolean encryptWithAES) {

        String finalBody = appendDisclaimerForExternalAddressesIfNeeded(emailAddress, body);
        if(encryptWithDES) {
            finalBody = encryptionService.encryptWithDES(finalBody);
        }
        if(encryptWithAES) {
            finalBody = encryptionService.encryptWithAES(finalBody);
        }

        emailClient.sendEmail(emailAddress, finalBody, asHTML);//todo try catch blah
    }

    private boolean isEmailAddressExternal(String emailAddress) {
        //just some basic crude guess...
        return emailAddress.split("@")[1].contains("external");
    }

    private String appendDisclaimerForExternalAddressesIfNeeded(String emailAddress, String body) {
        if(isEmailAddressExternal(emailAddress)) {
            return body + " " + propertiesService.getProperty("email.disclaimer");
        } else {
            return body;
        }
    }

    private List<String> extractAllAddresses(Email email) {
        List<String> result = new ArrayList<String>();
        result.addAll(email.getTo());
        result.addAll(email.getCc());
        result.addAll(email.getBcc());
        return result;
    }
}
