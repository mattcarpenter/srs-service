package net.mattcarpenter.benkyou.srsservice.service;

import com.google.common.collect.Lists;
import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.ItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CardService {

    private CardDao cardDao;
    private ItemDao itemDao;

    public CardService(CardDao cardDao, ItemDao itemDao) {
        this.cardDao = cardDao;
        this.itemDao = itemDao;
    }

    public CardEntity createCard(UUID itemId) {
        ItemEntity itemEntity = itemDao.findById(itemId).orElseThrow();

        CardEntity cardEntity = new CardEntity();
        cardEntity.setItemEntity(itemEntity);
        cardDao.save(cardEntity);

        return cardEntity;
    }

    public CardEntity getCard(UUID id) {
        return cardDao.findById(id).orElseThrow();
    }

    public List<CardEntity> getAllCards() {
        return Lists.newArrayList(cardDao.findAll());
    }
}
