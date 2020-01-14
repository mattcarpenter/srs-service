package net.mattcarpenter.benkyou.srsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.DeckDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class DataFillerService {
    private CardDao cardDao;
    private DeckDao deckDao;

    public DataFillerService(CardDao cardDao, DeckDao deckDao) {
        this.cardDao = cardDao;
        this.deckDao = deckDao;
    }

    @PostConstruct
    @Transactional
    public void fillData () throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        // Create a card
        CardEntity cardEntity = new CardEntity();
        cardDao.save(cardEntity);

        // Create another card
        CardEntity cardEntity2 = new CardEntity();
        cardDao.save(cardEntity2);

        System.out.println("\n\n\n\n\n-------------------------------------------\n\n\n\n\n");

        // Create a deck
        DeckEntity deckEntity = new DeckEntity();
        deckEntity.addCard(cardEntity);
        deckEntity.addCard(cardEntity2);
        deckDao.save(deckEntity);

        System.out.println("\n\n\n\n\n-------------------------------------------\n\n\n\n\n");
    }
}
