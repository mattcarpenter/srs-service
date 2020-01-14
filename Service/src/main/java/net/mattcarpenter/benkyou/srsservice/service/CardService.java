package net.mattcarpenter.benkyou.srsservice.service;

import com.google.common.collect.Lists;
import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CardService {

    private CardDao cardDao;

    public CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public CardEntity createCard() {
        CardEntity cardEntity = new CardEntity();
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
