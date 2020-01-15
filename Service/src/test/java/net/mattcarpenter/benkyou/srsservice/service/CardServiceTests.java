package net.mattcarpenter.benkyou.srsservice.service;

import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.CardFieldDao;
import net.mattcarpenter.benkyou.srsservice.dao.LayoutDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.CardFieldEntity;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutEntity;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutFieldEntity;
import net.mattcarpenter.benkyou.srsservice.model.CardFieldModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardServiceTests {

    private CardDao cardDao = Mockito.mock(CardDao.class);
    private LayoutDao layoutDao = Mockito.mock(LayoutDao.class);
    private CardFieldDao cardFieldDao = Mockito.mock(CardFieldDao.class);
    private CardService cardService;

    @Before
    public void before() {
        cardService = new CardService(cardDao, layoutDao, cardFieldDao);
    }

    @Test
    public void getCard_getsCard() {
        UUID id = UUID.randomUUID();
        CardEntity card = new CardEntity();
        when(cardDao.findById(id)).thenReturn(Optional.of(card));
        assertThat(cardService.getCard(id)).isEqualTo(card);
    }

    @Test
    public void getAllCards_ok() {
        CardEntity card = new CardEntity();
        when(cardDao.findAll()).thenReturn(Collections.singletonList(card));
        assertThat(cardService.getAllCards()).containsExactly(card);
    }

    @Test
    public void createCard_createsCard() {
        // set up data
        LayoutEntity layout = new LayoutEntity();
        LayoutFieldEntity layoutField = new LayoutFieldEntity();
        layoutField.setName("hiragana");
        layout.addField(layoutField);

        CardFieldModel cardField = new CardFieldModel();
        cardField.setName("hiragana");
        cardField.setValue("にほん");

        // captors
        ArgumentCaptor<CardFieldEntity> cardFieldCaptor = ArgumentCaptor.forClass(CardFieldEntity.class);
        ArgumentCaptor<CardEntity> cardCaptor = ArgumentCaptor.forClass(CardEntity.class);

        // mocks
        when(layoutDao.findById(layout.getId())).thenReturn(Optional.of(layout));

        cardService.createCard(layout.getId(), Collections.singletonList(cardField));

        // assertions
        verify(cardFieldDao).save(cardFieldCaptor.capture());
        verify(cardDao).save(cardCaptor.capture());

        assertThat(cardFieldCaptor.getValue().getValue()).isEqualTo("にほん");
        assertThat(cardFieldCaptor.getValue().getLayoutField().getName()).isEqualTo("hiragana");
        assertThat(cardCaptor.getValue().getFields().size()).isEqualTo(1);
    }

    @Test
    public void createCard_invalidField() {
        // set up data
        LayoutEntity layout = new LayoutEntity();
        LayoutFieldEntity layoutField = new LayoutFieldEntity();
        layoutField.setName("hiragana");
        layout.addField(layoutField);

        CardFieldModel cardField = new CardFieldModel();
        cardField.setName("invalid_field");
        cardField.setValue("にほん");

        // mocks
        when(layoutDao.findById(layout.getId())).thenReturn(Optional.of(layout));

        assertThatThrownBy(() -> cardService.createCard(layout.getId(), Collections.singletonList(cardField)));
    }

    @Test
    public void createCard_invalidLayout() {
        UUID invalidLayoutUUID = UUID.randomUUID();
        CardFieldModel cardField = new CardFieldModel();
        cardField.setName("invalid_field");
        cardField.setValue("にほん");

        // mocks
        when(layoutDao.findById(invalidLayoutUUID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> cardService.createCard(invalidLayoutUUID, Collections.singletonList(cardField)));
    }

    @Test
    public void deleteCard_ok() {
        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);
        UUID cardId = UUID.randomUUID();
        cardService.deleteCard(cardId);
        verify(cardDao).deleteById(captor.capture());
        assertThat(captor.getValue()).isEqualTo(cardId);
    }
}
