package net.mattcarpenter.benkyou.srsservice.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import net.mattcarpenter.benkyou.srsservice.entity.util.EntityWithUUID;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "Item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class ItemEntity extends EntityWithUUID {
    private UUID createdBy;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode data;
}
