package converters;

import model.Administrator;
import model.Client;
import model.ResourceAdministrator;
import model.User;
import modelView.AdministratorView;
import modelView.ClientView;
import modelView.ResourceAdministratorView;
import modelView.UserView;

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
}
