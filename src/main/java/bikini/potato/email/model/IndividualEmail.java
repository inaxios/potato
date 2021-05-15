package bikini.potato.email.model;

import java.util.LinkedList;
import java.util.Queue;

public class IndividualEmail {

    private String address;
    private String body;
    private boolean asHTML = false;
    private Queue<Encryption> encryptions = new LinkedList<Encryption>();
    private Integer attemptsLeft = 0;

    public IndividualEmail(String address, String body, boolean asHTML, Queue<Encryption> encryptions, Integer attemptsLeft) {
        this.address = address;
        this.body = body;
        this.asHTML = asHTML;
        this.encryptions = encryptions;
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

    public Integer getAttemptsLeft() {
        return attemptsLeft;
    }

    public void decreaseAttemptCount() {
        attemptsLeft--;
    }

    public Queue<Encryption> getEncryptions() {
        return encryptions;
    }
}
