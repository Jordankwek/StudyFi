package sg.edu.np.mad.Studyfi;

//Message Class
public class Message {
    String message;
    String senderID;
    String receiverID;
    long timeStamp;

    public Message() {
    }

    public Message(String message, String senderID, String receiverID, Long timeStamp) {
        this.message = message;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
