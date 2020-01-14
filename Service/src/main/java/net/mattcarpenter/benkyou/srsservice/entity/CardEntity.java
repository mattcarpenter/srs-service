package net.mattcarpenter.benkyou.srsservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import net.mattcarpenter.benkyou.srsservice.entity.util.EntityWithUUID;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Card")
@Data
public class CardEntity {

    // HashSet will not store 2+ CardEntities unless we either define id on this class (instead of extending
    // EntityWithUUID) or overriding Equals and HashCode.
    @Id
    @Type(type = "pg-uuid")
    private UUID id = UUID.randomUUID();

    private UUID createdBy;
}
