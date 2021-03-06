package net.mattcarpenter.benkyou.srsservice.service;

import com.google.common.collect.Lists;
import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.DeckDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeckService {

    private DeckDao deckDao;
    private CardDao cardDao;

    public DeckService(DeckDao deckDao, CardDao cardDao) {
        this.deckDao = deckDao;
        this.cardDao = cardDao;
    }

   public DeckEntity getDeck(UUID id) {
        return deckDao.findById(id).orElseThrow();
   }

   public List<DeckEntity> getAllDecks() {
        return Lists.newArrayList(deckDao.findAll());
   }

   public void addCardsToDeck(UUID deckId, List<UUID> cardIds) {
        DeckEntity deckEntity = deckDao.findById(deckId).orElseThrow();
        cardIds.forEach(id -> {
            CardEntity cardEntity = cardDao.findById(id).orElseThrow();
            deckEntity.addCard(cardEntity);
        });
        deckDao.save(deckEntity);
   }

   public DeckEntity createDeck(String title) {
        DeckEntity deck = new DeckEntity();
        deck.setTitle(title);
        deckDao.save(deck);
        return deck;
   }

   public DeckEntity removeCardFromDeck(UUID deckId, UUID cardId) {
        DeckEntity deck = getDeck(deckId);
        Optional<CardEntity> cardToRemove = deck.getCardEntities().stream()
                .filter(card -> cardId.equals(card.getId()))
                .findFirst();

        if (cardToRemove.isPresent()) {
            deck.removeCard(cardToRemove.get());
        } else {
            throw new RuntimeException("Card not present on the provided deck or card does not exist.");
        }

        deckDao.save(deck);
        return deck;
   }

   public void deleteDeck(UUID id) {
        deckDao.deleteById(id);
   }
}
