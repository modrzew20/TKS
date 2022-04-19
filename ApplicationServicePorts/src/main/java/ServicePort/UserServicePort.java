package ServicePort;

import exceptions.LoginInUseException;
import model.AccessLevel;
import java.util.List;
import java.util.UUID;

public interface UserServicePort<T> {
    List<T> readAllUser();

    T addUser(AccessLevel accessLevel, String login, String password) throws LoginInUseException;

    T updateUser(UUID uuid, String login, String password) throws
            LoginInUseException;

    T readOneUser(UUID uuid);

    List<T> readManyUser(String login);

    T deactivateUser(UUID uuid);

    T activateUser(UUID uuid);
}