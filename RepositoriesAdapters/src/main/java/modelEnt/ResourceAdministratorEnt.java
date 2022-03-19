package modelEnt;

import java.util.UUID;

public class ResourceAdministratorEnt extends UserEnt {
    public ResourceAdministratorEnt(UUID uuid, String login, String password, Boolean isActive, String accessLevel) {
        super(uuid, login, password, isActive, accessLevel);
    }
}
