package net.mattcarpenter.benkyou.srsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.DeckDao;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.dao.FieldDao;
import net.mattcarpenter.benkyou.srsservice.entity.Card;
import net.mattcarpenter.benkyou.srsservice.entity.Deck;
import net.mattcarpenter.benkyou.srsservice.entity.Item;
import net.mattcarpenter.benkyou.srsservice.entity.Field;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class DataFillerService {
    private FieldDao fieldDao;
    private ItemDao itemDao;
    private CardDao cardDao;
    private DeckDao deckDao;

    public DataFillerService(ItemDao itemDao, FieldDao fieldDao, CardDao cardDao, DeckDao deckDao) {
        this.fieldDao = fieldDao;
        this.itemDao = itemDao;
        this.cardDao = cardDao;
        this.deckDao = deckDao;
    }

    @PostConstruct
    @Transactional
    public void fillData () throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        // Create an item and fields
        Item item = new Item();
        Field field1 = new Field();
        Field field2 = new Field();
        field1.setData(mapper.readTree("{\"value\":\"eng\"}"));
        field2.setData(mapper.readTree("{\"value\":\"ひらがな\"}"));
        List<Field> fields = new ArrayList<>();
        fieldDao.save(field1);
        fieldDao.save(field2);
        fields.add(field1);
        fields.add(field2);
        item.setFields(fields);
        itemDao.save(item);

        // Create a card
        Card card = new Card();
        card.setBackField(field2);
        card.setFrontField(field1);
        card.setItem(item);
        cardDao.save(card);

        // Create another card
        Card card2 = new Card();
        card2.setBackField(field2);
        card2.setFrontField(field1);
        card2.setItem(item);
        cardDao.save(card2);

        System.out.println("\n\n\n\n\n-------------------------------------------\n\n\n\n\n");

        // Create a deck
        Deck deck = new Deck();
        deck.addCard(card);
        deck.addCard(card2);
        deckDao.save(deck);

        System.out.println("\n\n\n\n\n-------------------------------------------\n\n\n\n\n");
    }
}
