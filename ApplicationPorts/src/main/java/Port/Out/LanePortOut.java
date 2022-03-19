package Port.Out;

import com.example.kregielniaspring.model.Lane;

import java.util.List;
import java.util.UUID;

public interface LanePortOut {

    List<Lane> readAll();

    Lane readById(UUID uuid);
}
