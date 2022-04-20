package com.example.applicationsoap.soapConverters;

import com.example.applicationsoap.soapmodel.usermodel.AccessLevelType;
import com.example.applicationsoap.soapmodel.usermodel.UserSoap;
import model.AccessLevel;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserSoapConverter {

    public static UserSoap convertFromUser(User user) {
        if(user == null) return null;
        UserSoap userSoap = new UserSoap();
        switch (user.getAccessLevel()) {
            case Administrator -> userSoap.setAccessLevel(AccessLevelType.ADMINISTRATOR);
            case Client -> userSoap.setAccessLevel(AccessLevelType.CLIENT);
            case ResourceAdministrator -> userSoap.setAccessLevel(AccessLevelType.RESOURCE_ADMINISTRATOR);
        }
        userSoap.setLogin(user.getLogin());
        userSoap.setPassword(userSoap.getPassword());
        userSoap.setUuid(userSoap.getUuid());
        userSoap.setIsActive(user.getActive());
        return userSoap;
    }

    public static List<UserSoap> convertFromUserList(List<User> list) {
        List<UserSoap> userSoapList = new ArrayList<>();
        for ( User user : list) {
            userSoapList.add(convertFromUser(user));
        }
        return userSoapList;
    }

    public static AccessLevel convertToAccessLevel (AccessLevelType accessLevelType){
        switch (accessLevelType) {
            case ADMINISTRATOR -> {
                return AccessLevel.Administrator;
            }
            case CLIENT -> {
                return AccessLevel.Client;
            }
            case RESOURCE_ADMINISTRATOR -> {
                return AccessLevel.ResourceAdministrator;
            }
            default -> {return null;}
        }
    }






}
