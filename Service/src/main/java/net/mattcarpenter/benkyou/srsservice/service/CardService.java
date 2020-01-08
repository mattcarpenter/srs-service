package net.mattcarpenter.benkyou.srsservice.service;

import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.FieldDao;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.entity.Card;
import net.mattcarpenter.benkyou.srsservice.entity.Field;
import net.mattcarpenter.benkyou.srsservice.entity.Item;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardService {

    private CardDao cardDao;
    private FieldDao fieldDao;
    private ItemDao itemDao;

    public CardService(CardDao cardDao, FieldDao fieldDao, ItemDao itemDao) {
        this.cardDao = cardDao;
        this.fieldDao = fieldDao;
        this.itemDao = itemDao;
    }

    public Card createCard(UUID itemId, UUID frontFieldId, UUID backFieldId) {
        Field frontField = fieldDao.findById(frontFieldId).orElseThrow();
        Field backField = fieldDao.findById(backFieldId).orElseThrow();
        Item item = itemDao.findById(itemId).orElseThrow();

        Card card = new Card();
        card.setItem(item);
        card.setFrontField(frontField);
        card.setBackField(backField);
        cardDao.save(card);

        return card;
    }
}
