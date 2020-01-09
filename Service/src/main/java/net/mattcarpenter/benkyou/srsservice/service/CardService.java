package net.mattcarpenter.benkyou.srsservice.service;

import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.FieldDao;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.FieldEntity;
import net.mattcarpenter.benkyou.srsservice.entity.ItemEntity;
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

    public CardEntity createCard(UUID itemId, UUID frontFieldId, UUID backFieldId) {
        FieldEntity frontFieldEntity = fieldDao.findById(frontFieldId).orElseThrow();
        FieldEntity backFieldEntity = fieldDao.findById(backFieldId).orElseThrow();
        ItemEntity itemEntity = itemDao.findById(itemId).orElseThrow();

        CardEntity cardEntity = new CardEntity();
        cardEntity.setItemEntity(itemEntity);
        cardEntity.setFrontFieldEntity(frontFieldEntity);
        cardEntity.setBackFieldEntity(backFieldEntity);
        cardDao.save(cardEntity);

        return cardEntity;
    }

    public CardEntity getCard(UUID id) {
        return cardDao.findById(id).orElseThrow();
    }
}
