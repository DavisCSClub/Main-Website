package org.dcsc.athena.objects;

public class TuteeAuthorization {

    private String content;
    private String privateChannel;

    public TuteeAuthorization(String content, String privateChannel) {
        this.content = content;
        this.privateChannel = privateChannel;
    }

    public String getContent() {
        return content;
    }

    public String getPrivateChannel() {
        return privateChannel;
    }

}