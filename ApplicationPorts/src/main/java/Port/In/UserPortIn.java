package Port.In;


import exceptions.LoginInUseException;
import model.User;

import java.util.UUID;

public interface UserPortIn {

    User create(User user) throws LoginInUseException;

    User delete(UUID uuid);

    User update(User object) throws LoginInUseException;

    User activate(UUID uuid);

    User deactivate(UUID uuid);
}
