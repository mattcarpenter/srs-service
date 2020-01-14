package net.mattcarpenter.benkyou.srsservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "CardField")
@Data
@EqualsAndHashCode(exclude = "card")
public class CardFieldEntity {

    @Id
    @Type(type = "pg-uuid")
    private UUID id = UUID.randomUUID();

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    private CardEntity card;

    @ManyToOne(fetch = FetchType.LAZY)
    private LayoutFieldEntity layoutField;
}
