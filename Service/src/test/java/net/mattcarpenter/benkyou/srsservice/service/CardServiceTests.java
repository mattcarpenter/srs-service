package net.mattcarpenter.benkyou.srsservice.service;

import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.FieldDao;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.FieldEntity;
import net.mattcarpenter.benkyou.srsservice.entity.ItemEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardServiceTests {
    private CardDao cardDao = Mockito.mock(CardDao.class);
    private FieldDao fieldDao = Mockito.mock(FieldDao.class);
    private ItemDao itemDao = Mockito.mock(ItemDao.class);

    private CardService cardService;

    @Before
    public void before() {
        cardService = new CardService(cardDao, fieldDao, itemDao);
    }

    @Test
    public void getCard_getsCard() {
        UUID id = UUID.randomUUID();
        CardEntity card = new CardEntity(null, null, null, null);
        when(cardDao.findById(id)).thenReturn(Optional.of(card));
        assertThat(cardService.getCard(id)).isEqualTo(card);
    }

    @Test
    public void createCard_createsCard() throws Exception {
        ArgumentCaptor<CardEntity> captor = ArgumentCaptor.forClass(CardEntity.class);

        ItemEntity itemEntity = new ItemEntity();
        FieldEntity frontFieldEntity = new FieldEntity();
        FieldEntity backFieldEntity = new FieldEntity();

        when(itemDao.findById(itemEntity.getId())).thenReturn(Optional.of(itemEntity));
        when(fieldDao.findById(frontFieldEntity.getId())).thenReturn(Optional.of(frontFieldEntity));
        when(fieldDao.findById(backFieldEntity.getId())).thenReturn(Optional.of(backFieldEntity));

        cardService.createCard(itemEntity.getId(), frontFieldEntity.getId(), backFieldEntity.getId());

        verify(cardDao).save(captor.capture());

        assertThat(captor.getValue().getItemEntity().getId()).isEqualTo(itemEntity.getId());
        assertThat(captor.getValue().getBackFieldEntity().getId()).isEqualTo(backFieldEntity.getId());
        assertThat(captor.getValue().getFrontFieldEntity().getId()).isEqualTo(frontFieldEntity.getId());
    }
}
