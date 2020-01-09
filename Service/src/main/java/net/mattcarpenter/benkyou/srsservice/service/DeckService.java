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
}
