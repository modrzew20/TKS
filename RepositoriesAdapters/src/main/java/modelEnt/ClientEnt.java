package modelEnt;

import java.util.UUID;

public class ClientEnt extends UserEnt {
    public ClientEnt(UUID uuid, String login, String password, Boolean isActive, String accessLevel) {
        super(uuid, login, password, isActive, accessLevel);
    }
}
