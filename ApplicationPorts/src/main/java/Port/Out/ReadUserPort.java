package Port.Out;

import model.User;

import java.util.List;
import java.util.UUID;

public interface ReadUserPort {

    List<User> readAll();

    User readById(UUID uuid);

    List<User> readManyByLogin(String login);
}
