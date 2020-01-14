package net.mattcarpenter.benkyou.srsservice.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Layout")
@Data
public class LayoutEntity {

    @Id
    @Type(type = "pg-uuid")
    private UUID id = UUID.randomUUID();

    @OneToMany(
            mappedBy = "layout",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<LayoutFieldEntity> fields = new HashSet<>();

    public void addField(LayoutFieldEntity field) {
        fields.add(field);
        field.setLayout(this);
    }
}
