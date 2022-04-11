package Port.In;

import exceptions.LoginInUseException;
import model.User;

import java.util.UUID;

public interface UpdateUserPort {
    User update(User object) throws LoginInUseException;

    User activate(UUID uuid);

    User deactivate(UUID uuid);
}
