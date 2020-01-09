package net.mattcarpenter.benkyou.srsservice.mapper;

import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import net.mattcarpenter.benkyou.srsservice.entity.ItemEntity;
import net.mattcarpenter.benkyou.srsservice.model.DeckModel;
import net.mattcarpenter.benkyou.srsservice.model.ItemModel;

public class EntityToModelMapper {
    public static ItemModel mapToItemModel(ItemEntity entity) {
        return new ItemModel(entity.getId(), entity.getCreatedBy());
    }

    public static DeckModel mapToDeckModel(DeckEntity entity) {
        return new DeckModel(entity.getId(), entity.getCreatedBy(), entity.getTitle());
    }
}
