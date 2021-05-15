package bikini.potato.email.service.impl;

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
}
