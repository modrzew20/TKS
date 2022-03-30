package service;


import Port.In.CreateUserPort;
import Port.In.DeleteUserPort;
import Port.In.UpdateUserPort;
import Port.Out.ReadUserPort;
import exceptions.LoginInUseException;
import lombok.RequiredArgsConstructor;
import model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Object lock = new Object();
    private ReadUserPort readUserPort;
    private CreateUserPort createUserPort;
    private DeleteUserPort deleteUserPort;
    private UpdateUserPort updateUserPort;


    public List<User> readAllUser() {
        synchronized (lock) {
            return readUserPort.readAll();
        }
    }

    public User addUser(AccessLevel accessLevel, String login, String password) throws LoginInUseException {
        synchronized (lock) {
            switch (accessLevel) {
                case Administrator -> {
                    return createUserPort.create(new Administrator(UUID.randomUUID(), login, password, true));
                }
                case ResourceAdministrator -> {
                    return createUserPort.create(new ResourceAdministrator(UUID.randomUUID(), login, password, true));
                }
                case Client -> {
                    return createUserPort.create(new Client(UUID.randomUUID(), login, password, true));
                }
                default -> {
                    return null;
                }
            }
        }
    }

    public User updateUser(UUID uuid, String login, String password) throws
            LoginInUseException {
        synchronized (lock) {
            User user = readUserPort.readById(uuid);
            switch (user.getAccessLevel()) {
                case Administrator -> {
                    return updateUserPort.update(new Administrator(uuid, login, password, user.getActive()));
                }
                case ResourceAdministrator -> {
                    return updateUserPort.update(new ResourceAdministrator(uuid, login, password, user.getActive()));
                }
                case Client -> {
                    return updateUserPort.update(new Client(uuid, login, password, user.getActive()));
                }
                default -> {
                    return null;
                }
            }
        }
    }


    public User readOneUser(UUID uuid) {
        synchronized (lock) {
            return readUserPort.readById(uuid);
        }
    }

    public List<User> readManyUser(String login) {
        synchronized (lock) {
            return readUserPort.readManyByLogin(login);
        }
    }


    public User deactivateUser(UUID uuid) {
        synchronized (lock) {
            return updateUserPort.deactivate(uuid);
        }
    }

    public User activateUser(UUID uuid) {
        synchronized (lock) {
            return updateUserPort.activate(uuid);
        }
    }


}
