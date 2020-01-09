package net.mattcarpenter.benkyou.srsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.DeckDao;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.dao.FieldDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import net.mattcarpenter.benkyou.srsservice.entity.ItemEntity;
import net.mattcarpenter.benkyou.srsservice.entity.FieldEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        ItemEntity itemEntity = new ItemEntity();
        FieldEntity fieldEntity1 = new FieldEntity();
        FieldEntity fieldEntity2 = new FieldEntity();
        fieldEntity1.setData(mapper.readTree("{\"value\":\"eng\"}"));
        fieldEntity2.setData(mapper.readTree("{\"value\":\"ひらがな\"}"));
        List<FieldEntity> fieldEntities = new ArrayList<>();
        fieldDao.save(fieldEntity1);
        fieldDao.save(fieldEntity2);
        fieldEntities.add(fieldEntity1);
        fieldEntities.add(fieldEntity2);
        itemEntity.setFieldEntities(fieldEntities);
        itemDao.save(itemEntity);

        // Create a card
        CardEntity cardEntity = new CardEntity();
        cardEntity.setBackFieldEntity(fieldEntity2);
        cardEntity.setFrontFieldEntity(fieldEntity1);
        cardEntity.setItemEntity(itemEntity);
        cardDao.save(cardEntity);

        // Create another card
        CardEntity cardEntity2 = new CardEntity();
        cardEntity2.setBackFieldEntity(fieldEntity2);
        cardEntity2.setFrontFieldEntity(fieldEntity1);
        cardEntity2.setItemEntity(itemEntity);
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
