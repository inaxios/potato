package bikini.potato.email.service;


public interface EncryptionService {

    String encryptWithDES(String text);
    String encryptWithAES(String text);
}
