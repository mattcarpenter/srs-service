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
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Field extends EntityWithUUID {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode data;

    private UUID createdBy;
}
