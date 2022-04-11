package Port.Out;

import model.Lane;

import java.util.List;
import java.util.UUID;

public interface ReadLanePort {

    List<Lane> readAll();

    Lane readById(UUID uuid);
}
