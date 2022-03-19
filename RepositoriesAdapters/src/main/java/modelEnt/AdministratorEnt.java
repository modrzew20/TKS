package modelEnt;

import java.util.UUID;

public class AdministratorEnt extends UserEnt {

    public AdministratorEnt(UUID uuid, String login, String password, Boolean isActive, String accessLevel) {
        super(uuid, login, password, isActive, accessLevel);
    }
}
