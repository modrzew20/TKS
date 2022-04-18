package ServicePort;

import exceptions.LoginInUseException;
import model.AccessLevel;
import model.User;

import java.util.List;
import java.util.UUID;

public interface UserServicePort {
    List<User> readAllUser();

    User addUser(AccessLevel accessLevel, String login, String password) throws LoginInUseException;

    User updateUser(UUID uuid, String login, String password) throws
            LoginInUseException;

    User readOneUser(UUID uuid);

    List<User> readManyUser(String login);

    User deactivateUser(UUID uuid);

    User activateUser(UUID uuid);
}