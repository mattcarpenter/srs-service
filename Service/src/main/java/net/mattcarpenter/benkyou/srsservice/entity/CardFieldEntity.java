package net.mattcarpenter.benkyou.srsservice.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "CardField")
@Data
public class CardFieldEntity {

    @Id
    @Type(type = "pg-uuid")
    private UUID id = UUID.randomUUID();

    private String value;

    @ManyToOne
    private LayoutFieldEntity layoutField;
}
