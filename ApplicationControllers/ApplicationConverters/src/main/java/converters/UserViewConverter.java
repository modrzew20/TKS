package converters;

import model.Administrator;
import model.Client;
import model.ResourceAdministrator;
import model.User;
import modelView.AdministratorView;
import modelView.ClientView;
import modelView.ResourceAdministratorView;
import modelView.UserView;

import java.util.ArrayList;
import java.util.List;

public class UserViewConverter {

    public static User convertToUser(UserView userView) {
        switch (userView.getAccessLevel()) {
            case Administrator -> {
                return new Administrator(userView.getUuid(), userView.getLogin(), userView.getPassword(), userView.getActive());
            }
            case ResourceAdministrator -> {
                return new ResourceAdministrator(userView.getUuid(), userView.getLogin(), userView.getPassword(), userView.getActive());
            }
            case Client -> {
                return new Client(userView.getUuid(), userView.getLogin(), userView.getPassword(), userView.getActive());
            }
            default -> {
                return null;
            }
        }
    }

    public static List<User> convertToListUser(List<UserView> userViewList) {
        List<User> convertedList = new ArrayList<>();
        for (UserView user : userViewList) {
            convertedList.add(convertToUser(user));
        }
        return convertedList;
    }


    public static UserView convertFromUser(User user) {
        switch (user.getAccessLevel()) {
            case Administrator -> {
                return new AdministratorView(user.getUuid(), user.getLogin(), user.getPassword(), user.getActive());
            }
            case ResourceAdministrator -> {
                return new ResourceAdministratorView(user.getUuid(), user.getLogin(), user.getPassword(), user.getActive());
            }
            case Client -> {
                return new ClientView(user.getUuid(), user.getLogin(), user.getPassword(), user.getActive());
            }
            default -> {
                return null;
            }
        }
    }

    public static List<UserView> convertFromListUser(List<User> userList) {
        List<UserView> convertedList = new ArrayList<>();
        for (User user : userList) {
            convertedList.add(convertFromUser(user));
        }
        return convertedList;
    }
}
