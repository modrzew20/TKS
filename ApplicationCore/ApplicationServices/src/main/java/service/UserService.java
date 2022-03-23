package service;


import Port.In.UserPortIn;
import Port.Out.UserPortOut;
import exceptions.LoginInUseException;
import exceptions.LoginInUseExceptionEnt;
import lombok.RequiredArgsConstructor;
import model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Object lock = new Object();
    private UserPortOut userPortOut;
    private UserPortIn userPortIn;

    public List<User> readAllUser() {
        synchronized (lock) {
            return userPortOut.readAll();
        }
    }

    public User addUser(AccessLevel accessLevel, String login, String password) throws LoginInUseExceptionEnt, LoginInUseException {
        synchronized (lock) {
            switch (accessLevel) {
                case Administrator -> {
                    return userPortIn.create(new Administrator(UUID.randomUUID(), login, password, true));
                }
                case ResourceAdministrator -> {
                    return userPortIn.create(new ResourceAdministrator(UUID.randomUUID(), login, password, true));
                }
                case Client -> {
                    return userPortIn.create(new Client(UUID.randomUUID(), login, password, true));
                }
                default -> {
                    return null;
                }
            }
        }
    }

    public User updateUser(UUID uuid, String login, String password) throws
            LoginInUseException, LoginInUseExceptionEnt {
        synchronized (lock) {
            User user = userPortOut.readById(uuid);
            switch (user.getAccessLevel()) {
                case Administrator -> {
                    return userPortIn.update(new Administrator(uuid, login, password, user.getActive()));
                }
                case ResourceAdministrator -> {
                    return userPortIn.update(new ResourceAdministrator(uuid, login, password, user.getActive()));
                }
                case Client -> {
                    return userPortIn.update(new Client(uuid, login, password, user.getActive()));
                }
                default -> {
                    return null;
                }
            }
        }
    }


    public User readOneUser(UUID uuid) {
        synchronized (lock) {
            return userPortOut.readById(uuid);
        }
    }

    public List<User> readManyUser(String login) {
        synchronized (lock) {
            return userPortOut.readManyByLogin(login);
        }
    }


    public User deactivateUser(UUID uuid) {
        synchronized (lock) {
            return userPortIn.deactivate(uuid);
        }
    }

    public User activateUser(UUID uuid) {
        synchronized (lock) {
            return userPortIn.activate(uuid);
        }
    }


}
