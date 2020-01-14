package net.mattcarpenter.benkyou.srsservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "LayoutField")
@Data
@EqualsAndHashCode(exclude = "layout")
public class LayoutFieldEntity {

    @Id
    @Type(type = "pg-uuid")
    private UUID id = UUID.randomUUID();

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private LayoutEntity layout;
}
