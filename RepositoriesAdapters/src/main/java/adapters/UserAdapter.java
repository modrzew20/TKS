package adapters;

import com.example.kregielniaspring.model.Administrator;
import com.example.kregielniaspring.model.Client;
import com.example.kregielniaspring.model.ResourceAdministrator;
import com.example.kregielniaspring.model.User;
import modelEnt.AdministratorEnt;
import modelEnt.ClientEnt;
import modelEnt.ResourceAdministratorEnt;
import modelEnt.UserEnt;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter {

    public User convertToUser(UserEnt userEnt) {
        if (userEnt.getClass() == ClientEnt.class) {
            return new Client(userEnt.getUuid(), userEnt.getLogin()
                    , userEnt.getPassword(), userEnt.getActive());
        }
        if (userEnt.getClass() == AdministratorEnt.class) {
            return new Administrator(userEnt.getUuid(), userEnt.getLogin()
                    , userEnt.getPassword(), userEnt.getActive());
        }
        if (userEnt.getClass() == ResourceAdministratorEnt.class) {
            return new ResourceAdministrator(userEnt.getUuid(), userEnt.getLogin()
                    , userEnt.getPassword(), userEnt.getActive());
        }
        return null;
    }

    public List<User> convertToListUser(List<UserEnt> userEntList) {
        List<User> convertedList = new ArrayList<>();
        for (UserEnt user : userEntList) {
            convertedList.add(convertToUser(user));
        }
        return convertedList;
    }


    public UserEnt convertFromUser(User user) {
        if (user.getClass() == Client.class) {
            return new ClientEnt(user.getUuid(), user.getLogin()
                    , user.getPassword(), user.getActive());
        }
        if (user.getClass() == Administrator.class) {
            return new AdministratorEnt(user.getUuid(), user.getLogin()
                    , user.getPassword(), user.getActive());
        }
        if (user.getClass() == ResourceAdministrator.class) {
            return new ResourceAdministratorEnt(user.getUuid(), user.getLogin()
                    , user.getPassword(), user.getActive());
        }
        return null;
    }

    public List<UserEnt> convertFromListUser(List<User> userList) {
        List<UserEnt> convertedList = new ArrayList<>();
        for (User user : userList) {
            convertedList.add(convertFromUser(user));
        }
        return convertedList;
    }
}
