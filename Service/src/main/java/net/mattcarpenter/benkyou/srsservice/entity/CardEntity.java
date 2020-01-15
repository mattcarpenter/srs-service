package net.mattcarpenter.benkyou.srsservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Card")
@Data
public class CardEntity {

    @Id
    @Type(type = "pg-uuid")
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private LayoutEntity layout;

    @OneToMany(
            mappedBy = "card",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CardFieldEntity> fields = new HashSet<>();

    public void addField(CardFieldEntity field) {
        fields.add(field);
        field.setCard(this);
    }

    private UUID createdBy;
}
