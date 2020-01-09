package net.mattcarpenter.benkyou.srsservice.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import net.mattcarpenter.benkyou.srsservice.entity.util.EntityWithUUID;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "Item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity extends EntityWithUUID {
    private UUID createdBy;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_card_field"))
    private List<FieldEntity> fieldEntities;
}
