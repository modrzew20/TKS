package ServicePort;

import exceptions.LoginInUseException;
import model.AccessLevel;
import model.User;
import modelView.UserView;

import java.util.List;
import java.util.UUID;

public interface UserServicePort {
    List<UserView> readAllUser();

    UserView addUser(AccessLevel accessLevel, String login, String password) throws LoginInUseException;

    UserView updateUser(UUID uuid, String login, String password) throws
            LoginInUseException;

    UserView readOneUser(UUID uuid);

    List<UserView> readManyUser(String login);

    UserView deactivateUser(UUID uuid);

    UserView activateUser(UUID uuid);
}