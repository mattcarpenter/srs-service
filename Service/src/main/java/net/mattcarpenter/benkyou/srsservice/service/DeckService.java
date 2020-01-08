package net.mattcarpenter.benkyou.srsservice.service;

import com.google.common.collect.Lists;
import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.DeckDao;
import net.mattcarpenter.benkyou.srsservice.entity.Card;
import net.mattcarpenter.benkyou.srsservice.entity.Deck;
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

   public Deck getDeck(UUID id) {
        return deckDao.findById(id).orElseThrow();
   }

   public List<Deck> getAllDecks() {
        return Lists.newArrayList(deckDao.findAll());
   }

   public void addCardsToDeck(UUID deckId, List<UUID> cardIds) {
        Deck deck = deckDao.findById(deckId).orElseThrow();
        cardIds.forEach(id -> {
            Card card = cardDao.findById(id).orElseThrow();
            deck.addCard(card);
        });
        deckDao.save(deck);
   }
}
