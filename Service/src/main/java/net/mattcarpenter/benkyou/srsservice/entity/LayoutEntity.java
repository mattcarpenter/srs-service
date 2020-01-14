package net.mattcarpenter.benkyou.srsservice.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Layout")
@Data
public class LayoutEntity {

    @Id
    @Type(type = "pg-uuid")
    private UUID id = UUID.randomUUID();

    @OneToMany
    private Set<LayoutFieldEntity> fields;
}
