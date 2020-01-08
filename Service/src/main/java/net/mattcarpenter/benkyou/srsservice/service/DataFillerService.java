package net.mattcarpenter.benkyou.srsservice.service;

import net.mattcarpenter.benkyou.srsservice.dao.CardDAO;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDAO;
import net.mattcarpenter.benkyou.srsservice.dao.FieldDAO;
import net.mattcarpenter.benkyou.srsservice.entity.Card;
import net.mattcarpenter.benkyou.srsservice.entity.Item;
import net.mattcarpenter.benkyou.srsservice.entity.Field;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class DataFillerService {
    private FieldDAO fieldDAO;
    private ItemDAO itemDAO;
    private CardDAO cardDAO;

    public DataFillerService(ItemDAO itemDAO, FieldDAO fieldDAO, CardDAO cardDAO) {
        this.fieldDAO = fieldDAO;
        this.itemDAO = itemDAO;
        this.cardDAO = cardDAO;
    }

    @PostConstruct
    @Transactional
    public void fillData (){
        // Create an item and fields
        Item item = new Item();
        Field field1 = new Field();
        Field field2 = new Field();
        field1.setData("{\"value\":\"eng\"}");
        field2.setData("{\"value\":\"ひらがな\"}");
        field1.setItem(item);
        field2.setItem(item);
        itemDAO.save(item);
        fieldDAO.save(field1);
        fieldDAO.save(field2);

        // Create a card
        Card card = new Card();
        card.setBackField(field2);
        card.setFrontField(field1);
        //card.setItem(item);
        cardDAO.save(card);

        System.out.println("FOO");
    }
}
