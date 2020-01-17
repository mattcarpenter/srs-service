package net.mattcarpenter.benkyou.srsservice.service;

import com.google.common.collect.Lists;
import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.CardFieldDao;
import net.mattcarpenter.benkyou.srsservice.dao.LayoutDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.CardFieldEntity;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutEntity;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutFieldEntity;
import net.mattcarpenter.benkyou.srsservice.model.CardFieldModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class CardService {

    private CardDao cardDao;
    private LayoutDao layoutDao;
    private CardFieldDao cardFieldDao;

    public CardService(CardDao cardDao, LayoutDao layoutDao, CardFieldDao cardFieldDao) {
        this.cardDao = cardDao;
        this.layoutDao = layoutDao;
        this.cardFieldDao = cardFieldDao;
    }

    public CardEntity createCard(UUID layoutId, List<CardFieldModel> cardFields) {
        LayoutEntity layout = layoutDao.findById(layoutId).orElseThrow();
        Set<LayoutFieldEntity> layoutFields = layout.getFields();

        CardEntity cardEntity = new CardEntity();
        cardEntity.setLayout(layout);

        // Create fields
        cardFields.forEach(cardField -> {
            CardFieldEntity cardFieldEntity = new CardFieldEntity();
            cardFieldEntity.setValue(cardField.getValue());
            cardFieldEntity.setName(cardField.getName());

            Optional<LayoutFieldEntity> layoutFieldEntity = layoutFields.stream()
                    .filter(field -> field.getName().equals(cardField.getName()))
                    .findFirst();

            if (layoutFieldEntity.isPresent()) {
                //cardFieldEntity.setLayoutField(layoutFieldEntity.get());
                cardFieldDao.save(cardFieldEntity);
                cardEntity.addField(cardFieldEntity);
            } else {
                throw new RuntimeException("Provided field does not match the card's layout.");
            }
        });

        cardDao.save(cardEntity);
        return cardEntity;
    }

    public CardEntity getCard(UUID id) {
        return cardDao.findById(id).orElseThrow();
    }

    public List<CardEntity> getAllCards() {
        return Lists.newArrayList(cardDao.findAll());
    }

    public void deleteCard(UUID id) {
        cardDao.deleteById(id);
    }
}
