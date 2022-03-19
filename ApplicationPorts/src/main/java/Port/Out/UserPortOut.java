package Port.Out;

import com.example.kregielniaspring.model.User;

import java.util.List;
import java.util.UUID;

public interface UserPortOut {

    List<User> readAll();

    User readById(UUID uuid);
}
