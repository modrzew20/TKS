package Port.In;

import model.Lane;

import java.util.UUID;

public interface DeleteLanePort {
    Lane delete(UUID uuid);
}
