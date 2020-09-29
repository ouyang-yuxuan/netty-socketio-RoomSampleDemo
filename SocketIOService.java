/**
 * 功能描述
 * @author ouya
 */
@Slf4j
@Service(value = "socketIOService")
public class SocketIOService{
  
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
}

