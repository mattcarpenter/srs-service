package net.mattcarpenter.benkyou.srsservice.mapper;

import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import net.mattcarpenter.benkyou.srsservice.model.DeckModel;

public class EntityToModelMapper {
    public static DeckModel mapToDeckModel(DeckEntity entity) {
        return new DeckModel(entity.getId(), entity.getCreatedBy(), entity.getTitle());
    }
}
