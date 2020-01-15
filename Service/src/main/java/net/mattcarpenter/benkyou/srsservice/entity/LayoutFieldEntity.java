package net.mattcarpenter.benkyou.srsservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @JsonIgnore
    private LayoutEntity layout;
}
