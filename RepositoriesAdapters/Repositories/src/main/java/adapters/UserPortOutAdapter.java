package adapters;

import Port.Out.ReadUserPort;
import exceptions.LoginInUseExceptionEnt;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.UserRepository;

import java.util.List;
import java.util.UUID;

import static converters.UserConverter.convertToListUser;
import static converters.UserConverter.convertToUser;

@Component
public class UserPortOutAdapter implements ReadUserPort {

    @Autowired
    UserRepository userRepository;

    public UserPortOutAdapter() throws LoginInUseExceptionEnt {
    }

    @Override
    public List<User> readAll() {
        return convertToListUser(userRepository.readAll());
    }

    @Override
    public User readById(UUID uuid) {
        return convertToUser(userRepository.readById(uuid));
    }

    @Override
    public List<User> readManyByLogin(String login) {
        return convertToListUser(userRepository.readManyByLogin(login));
    }
}
