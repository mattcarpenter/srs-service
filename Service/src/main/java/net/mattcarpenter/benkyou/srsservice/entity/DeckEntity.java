package net.mattcarpenter.benkyou.srsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.mattcarpenter.benkyou.srsservice.entity.util.EntityWithUUID;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Deck")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeckEntity extends EntityWithUUID {
    private String title;
    private UUID createdBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "deck_card",
            joinColumns = @JoinColumn(name = "deck_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"))
    private Set<CardEntity> cardEntities = new HashSet<>();

    public void addCard(CardEntity card) {
        cardEntities.add(card);
    }

    public void removeCard(CardEntity card) {
        cardEntities.remove(card);
    }
}
