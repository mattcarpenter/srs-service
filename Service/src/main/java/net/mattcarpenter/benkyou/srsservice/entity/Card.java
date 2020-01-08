package net.mattcarpenter.benkyou.srsservice.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import net.mattcarpenter.benkyou.srsservice.entity.util.EntityWithUUID;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card extends EntityWithUUID {

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_card_item"))
    private Item item;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_card_frontitemfield"))
    private Field frontField;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_card_backitemfield"))
    private Field backField;
}
