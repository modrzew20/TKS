package com.example.kregielniaspring.service;


import Port.In.UserPortIn;
import Port.Out.UserPortOut;
import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.Administrator;
import com.example.kregielniaspring.model.Client;
import com.example.kregielniaspring.model.ResourceAdministrator;
import com.example.kregielniaspring.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserPortOut userPortOut;
    private UserPortIn userPortIn;

    private final Object lock = new Object();


    public List<User> readAllUser() {
        synchronized (lock) {
            return userPortOut.readAll();
        }
    }

    public User addUser(String accessLevel, String login, String password) throws LoginInUseException {
        synchronized (lock) {
            if (accessLevel.equals("ResourceAdministrator"))
                return userPortIn.create(new ResourceAdministrator(UUID.randomUUID(), login, password, true, "ResourceAdministrator"));
            if (accessLevel.equals("Administrator"))
                return userPortIn.create(new Administrator(UUID.randomUUID(), login, password, true, "Administrator"));
            if (accessLevel.equals("Client"))
                return userPortIn.create(new Client(UUID.randomUUID(), login, password, true, "Client"));
            return null;
        }
    }

    public User updateUser(UUID uuid, String login, String password) throws LoginInUseException {
        synchronized (lock) {
            User user = userPortOut.readById(uuid);
            String accessLevel = user.getClass().getSimpleName();
            if (accessLevel.equals("ResourceAdministrator"))
                return userPortIn.update(new ResourceAdministrator(uuid, login, password, user.getActive(), "ResourceAdministrator"));
            if (accessLevel.equals("Administrator"))
                return userPortIn.update(new Administrator(uuid, login, password, user.getActive(), "Administrator"));
            if (accessLevel.equals("Client"))
                return userPortIn.update(new Client(uuid, login, password, user.getActive(), "Client"));
            return null;
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
