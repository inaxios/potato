package bikini.potato.email.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Email {

    private String subject;
    private List<String> to = new ArrayList<String>();
    private List<String> cc = new ArrayList<String>();
    private List<String> bcc = new ArrayList<String>();
    private String body;
    private boolean asHTML = false;
    private Queue<Encryption> encryptions = new LinkedList<Encryption>();

    public boolean isAsHTML() {
        return asHTML;
    }

    public void setAsHTML(boolean asHTML) {
        this.asHTML = asHTML;
    }

    public void addTO(String emailAddress) {
        if(to == null) {
            to = new ArrayList<String>();
        }
        to.add(emailAddress);
    }

    public void addCC(String emailAddress) {
        if(cc == null) {
            cc = new ArrayList<String>();
        }
        cc.add(emailAddress);
    }

    public void addBCC(String emailAddress) {
        if(bcc == null) {
            bcc = new ArrayList<String>();
        }
        bcc.add(emailAddress);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addEncryption(Encryption algorithm) {
        if(encryptions == null) {
            encryptions = new LinkedList<Encryption>();
        }
        encryptions.add(algorithm);
    }

    public Queue<Encryption> getEncryptions() {
        return encryptions;
    }
}
