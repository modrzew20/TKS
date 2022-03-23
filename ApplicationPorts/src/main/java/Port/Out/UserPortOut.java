package Port.Out;

import model.User;

import java.util.List;
import java.util.UUID;

public interface UserPortOut {

    List<User> readAll();

    User readById(UUID uuid);

    List<User> readManyByLogin(String login);
}
