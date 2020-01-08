package net.mattcarpenter.benkyou.srsservice.service;

import net.mattcarpenter.benkyou.srsservice.dao.DeckDao;
import net.mattcarpenter.benkyou.srsservice.entity.Deck;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeckService {

    private DeckDao deckDao;

    public DeckService(DeckDao deckDao) {
        this.deckDao = deckDao;
    }

   public Deck getDeck(UUID id) {
        return deckDao.findById(id).orElseThrow();
   }
}
