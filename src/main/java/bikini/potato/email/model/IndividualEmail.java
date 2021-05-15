package bikini.potato.email.model;

public class IndividualEmail {

    private String address;
    private String body;
    private boolean asHTML = false;
    private boolean encryptedWithDES = false;
    private boolean encryptedWithAES = false;
    private Integer attemptsLeft = 0;

    public IndividualEmail(String address, String body, boolean asHTML, boolean encryptedWithDES, boolean encryptedWithAES, Integer attemptsLeft) {
        this.address = address;
        this.body = body;
        this.asHTML = asHTML;
        this.encryptedWithDES = encryptedWithDES;
        this.encryptedWithAES = encryptedWithAES;
        this.attemptsLeft = attemptsLeft;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isAsHTML() {
        return asHTML;
    }

    public void setAsHTML(boolean asHTML) {
        this.asHTML = asHTML;
    }

    public boolean isEncryptedWithDES() {
        return encryptedWithDES;
    }

    public void setEncryptedWithDES(boolean encryptedWithDES) {
        this.encryptedWithDES = encryptedWithDES;
    }

    public boolean isEncryptedWithAES() {
        return encryptedWithAES;
    }

    public void setEncryptedWithAES(boolean encryptedWithAES) {
        this.encryptedWithAES = encryptedWithAES;
    }

    public Integer getAttemptsLeft() {
        return attemptsLeft;
    }

    public void decreaseAttemptCount() {
        attemptsLeft--;
    }
}
