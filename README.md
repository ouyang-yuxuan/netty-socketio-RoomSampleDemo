# netty-socketio-RoomSampleDemo
A simple example of setting-up dynamic "rooms" for netty-socket.io

SocketReqDTO.java

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

SocketIOService.java
public static ConcurrentMap<Integer, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();
public static ConcurrentMap<UUID, Integer> infoMap = new ConcurrentHashMap<>();
@OnEvent(value = "teamRoom")
public void teamRoom(SocketIOClient client, AckRequest request, SocketReqDTO socketReqDTO) {
    log.info(socketReqDTO.toString());
    for (SocketIOClient socketIOClient : socketIOClientMap.values()) {
        for (String room : socketIOClient.getAllRooms()) {
            log.info(room);
            if (room.equals(socketReqDTO.getRoomName())){
                socketIOClient.sendEvent(socketReqDTO.getEVENT(), socketReqDTO.getContent());
            }
        }
    }
}
@OnEvent(value = "createTeam")
public void createTeam(SocketIOClient client, AckRequest request) {
    String roomName = "/team/"+infoMap.get(client.getSessionId())+"t";
    client.joinRoom(roomName);
}
