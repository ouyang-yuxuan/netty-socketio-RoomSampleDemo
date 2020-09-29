
/**
 * @author ouya
 */
public class SocketReqDTO {
    private String EVENT;
    private String roomName;
    private String content;

    public String getEVENT() {
        return EVENT;
    }

    public void setEVENT(String EVENT) {
        this.EVENT = EVENT;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SocketReqDTO{" +
                "EVENT='" + EVENT + '\'' +
                ", roomName='" + roomName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
