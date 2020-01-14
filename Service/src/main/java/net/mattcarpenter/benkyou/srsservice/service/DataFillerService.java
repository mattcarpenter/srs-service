package net.mattcarpenter.benkyou.srsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mattcarpenter.benkyou.srsservice.dao.LayoutDao;
import net.mattcarpenter.benkyou.srsservice.dao.LayoutFieldDao;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutEntity;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutFieldEntity;
import net.mattcarpenter.benkyou.srsservice.model.CardFieldModel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class DataFillerService {

    LayoutDao layoutDao;
    LayoutFieldDao layoutFieldDao;
    CardService cardService;

    public DataFillerService(CardService cardService, LayoutDao layoutDao, LayoutFieldDao layoutFieldDao) {
        this.cardService = cardService;
        this.layoutDao = layoutDao;
        this.layoutFieldDao = layoutFieldDao;
    }

    @PostConstruct
    @Transactional
    public void fillData () throws Exception {
        ObjectMapper mapper = new ObjectMapper();


        // Create a layout
        LayoutFieldEntity field1 = new LayoutFieldEntity();
        field1.setName("english");
        //layoutFieldDao.save(field1);

        LayoutFieldEntity field2 = new LayoutFieldEntity();
        field2.setName("hiragana");
        //layoutFieldDao.save(field2);

        // Create a layout
        LayoutEntity layout = new LayoutEntity();
        layout.addField(field1);
        layout.addField(field2);

        layoutDao.save(layout);

        // Create a card
        CardFieldModel cardField1 = new CardFieldModel();
        cardField1.setName("english");
        cardField1.setValue("Japan");

        CardFieldModel cardField2 = new CardFieldModel();
        cardField2.setName("hiragana");
        cardField2.setValue("にほん");

        cardService.createCard(layout.getId(), Arrays.asList(cardField1, cardField2));
    }
}
