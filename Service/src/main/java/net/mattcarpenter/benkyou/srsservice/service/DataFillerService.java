package net.mattcarpenter.benkyou.srsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.dao.FieldDao;
import net.mattcarpenter.benkyou.srsservice.entity.Card;
import net.mattcarpenter.benkyou.srsservice.entity.Item;
import net.mattcarpenter.benkyou.srsservice.entity.Field;
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

    public DataFillerService(ItemDao itemDao, FieldDao fieldDao, CardDao cardDao) {
        this.fieldDao = fieldDao;
        this.itemDao = itemDao;
        this.cardDao = cardDao;
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

        System.out.println("FOO");
    }
}
