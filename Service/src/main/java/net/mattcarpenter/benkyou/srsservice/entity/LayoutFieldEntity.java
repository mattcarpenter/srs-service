package net.mattcarpenter.benkyou.srsservice.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "LayoutField")
@Data
public class LayoutFieldEntity {

    @Id
    @Type(type = "pg-uuid")
    private UUID id = UUID.randomUUID();

    @ManyToOne
    private LayoutEntity layout;

    private String name;
}
