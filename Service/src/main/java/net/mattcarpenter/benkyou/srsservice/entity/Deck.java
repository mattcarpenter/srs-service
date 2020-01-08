package net.mattcarpenter.benkyou.srsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.mattcarpenter.benkyou.srsservice.entity.util.EntityWithUUID;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deck extends EntityWithUUID {
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "deck_card",
            joinColumns = @JoinColumn(name = "deck_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"))
    private Set<Card> cards;
}
