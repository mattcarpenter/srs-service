package net.mattcarpenter.benkyou.srsservice.mapper;

import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.CardFieldEntity;
import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import net.mattcarpenter.benkyou.srsservice.model.CardFieldModel;
import net.mattcarpenter.benkyou.srsservice.model.CardModel;
import net.mattcarpenter.benkyou.srsservice.model.DeckModel;

import java.util.List;
import java.util.stream.Collectors;

public class EntityToModelMapper {
    public static DeckModel mapToDeckModel(DeckEntity entity) {
        return new DeckModel(entity.getId(), entity.getCreatedBy(), entity.getTitle());
    }

    public static CardFieldModel mapToCardFieldModel(CardFieldEntity entity) {
        return CardFieldModel.builder().name(entity.getName()).value(entity.getValue()).build();
    }

    public static CardModel mapToCardModel(CardEntity entity) {
        List<CardFieldModel> fields = entity.getFields().stream()
                .map(EntityToModelMapper::mapToCardFieldModel)
                .collect(Collectors.toList());
        return CardModel.builder()
                .id(entity.getId())
                .layoutId(entity.getLayout().getId())
                .fields(fields).build();
    }
}
