package net.mattcarpenter.benkyou.srsservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import net.mattcarpenter.benkyou.srsservice.entity.util.EntityWithUUID;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Card")
@Data
public class CardEntity {

    @Id
    @Type(type = "pg-uuid")
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_card_item"))
    private ItemEntity itemEntity;

    private UUID createdBy;
}
