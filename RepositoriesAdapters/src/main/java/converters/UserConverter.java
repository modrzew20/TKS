package converters;

import model.Administrator;
import model.Client;
import model.ResourceAdministrator;
import model.User;
import modelEnt.AdministratorEnt;
import modelEnt.ClientEnt;
import modelEnt.ResourceAdministratorEnt;
import modelEnt.UserEnt;
import modelEnt.AccessLevelEnt;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static User convertToUser(UserEnt userEnt) {
        switch (userEnt.getAccessLevel()) {
            case Administrator -> {
                return new Administrator(userEnt.getUuid(), userEnt.getLogin(), userEnt.getPassword(), userEnt.getActive());
            }
            case ResourceAdministrator -> {
                return new ResourceAdministrator(userEnt.getUuid(), userEnt.getLogin(), userEnt.getPassword(), userEnt.getActive());
            }
            case Client -> {
                return new Client(userEnt.getUuid(), userEnt.getLogin(), userEnt.getPassword(), userEnt.getActive());
            }
            default -> {
                return null;
            }
        }
    }

    public static List<User> convertToListUser(List<UserEnt> userEntList) {
        List<User> convertedList = new ArrayList<>();
        for (UserEnt user : userEntList) {
            convertedList.add(convertToUser(user));
        }
        return convertedList;
    }


    public static UserEnt convertFromUser(User user) {
        switch (user.getAccessLevel()) {
            case Administrator -> {
                return new AdministratorEnt(user.getUuid(), user.getLogin(), user.getPassword(), user.getActive());
            }
            case ResourceAdministrator -> {
                return new ResourceAdministratorEnt(user.getUuid(), user.getLogin(), user.getPassword(), user.getActive());
            }
            case Client -> {
                return new ClientEnt(user.getUuid(), user.getLogin(), user.getPassword(), user.getActive());
            }
            default -> {
                return null;
            }
        }
    }

    public static List<UserEnt> convertFromListUser(List<User> userList) {
        List<UserEnt> convertedList = new ArrayList<>();
        for (User user : userList) {
            convertedList.add(convertFromUser(user));
        }
        return convertedList;
    }
}
