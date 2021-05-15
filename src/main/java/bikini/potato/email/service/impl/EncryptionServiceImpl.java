package bikini.potato.email.service.impl;

import bikini.potato.email.model.Encryption;
import bikini.potato.email.service.EncryptionService;

public class EncryptionServiceImpl implements EncryptionService {

    @Override
    public String encryptWithDES(String text) {
        return "<DES-ENCRYPTED>" + text + "</DES-ENCRYPTED>";
    }

    @Override
    public String encryptWithAES(String text) {
        return "<AES-ENCRYPTED>" + text + "</AES-ENCRYPTED>";
    }

    @Override
    public String encryptWithAlgorithm(String text, Encryption algorithm) {
        if(Encryption.DES.equals(algorithm)) {
            return encryptWithDES(text);
        }
        else if(Encryption.AES.equals(algorithm)) {
            return encryptWithAES(text);
        }
        return null;
    }
}
