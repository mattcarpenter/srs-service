package net.mattcarpenter.benkyou.srsservice.entity.util;

import lombok.Data;
import org.hibernate.annotations.Type;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
public class EntityWithUUID {

    @Id
    @Type(type = "pg-uuid")
    public UUID id;

    public EntityWithUUID() {
        this.id = UUID.randomUUID();
    }
}
