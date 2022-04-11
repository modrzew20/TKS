package Port.In;

import model.User;

import java.util.UUID;

public interface DeleteUserPort {
    User delete(UUID uuid);
}
