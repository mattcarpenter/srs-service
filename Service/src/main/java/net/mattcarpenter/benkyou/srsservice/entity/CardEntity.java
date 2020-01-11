package net.mattcarpenter.benkyou.srsservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import net.mattcarpenter.benkyou.srsservice.entity.util.EntityWithUUID;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity extends EntityWithUUID {

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_card_item"))
    @JsonIgnore
    private ItemEntity itemEntity;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_card_frontitemfield"))
    private FieldEntity frontFieldEntity;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_card_backitemfield"))
    private FieldEntity backFieldEntity;

    private UUID createdBy;
}
