package bikini.potato.email.service;


import bikini.potato.email.model.Encryption;

public interface EncryptionService {

    String encryptWithDES(String text);
    String encryptWithAES(String text);
    String encryptWithAlgorithm(String text, Encryption algorithm);
}
