package net.mattcarpenter.benkyou.srsservice.mapper;

import net.mattcarpenter.benkyou.srsservice.entity.LayoutEntity;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutFieldEntity;
import net.mattcarpenter.benkyou.srsservice.model.LayoutFieldModel;

public class ModelToEntityMapper {
    public static LayoutFieldEntity mapToLayoutFieldEntity(LayoutFieldModel model) {
        LayoutFieldEntity entity = new LayoutFieldEntity();
        entity.setName(model.getName());
        return entity;
    }
}
