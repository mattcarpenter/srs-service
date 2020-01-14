package net.mattcarpenter.benkyou.srsservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import net.mattcarpenter.benkyou.srsservice.entity.util.EntityWithUUID;
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

    @OneToOne(fetch = FetchType.LAZY)
    private LayoutEntity layout;

    public LayoutEntity getLayout() {
        return this.layout;
    }

    @OneToMany
    private Set<CardFieldEntity> fields = new HashSet<>();

    public void addField(CardFieldEntity field) {
        fields.add(field);
    }

    private UUID createdBy;
}
