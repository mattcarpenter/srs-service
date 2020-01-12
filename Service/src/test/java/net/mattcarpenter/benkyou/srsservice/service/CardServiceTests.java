package net.mattcarpenter.benkyou.srsservice.service;

import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.ItemEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardServiceTests {
    private CardDao cardDao = Mockito.mock(CardDao.class);
    private ItemDao itemDao = Mockito.mock(ItemDao.class);

    private CardService cardService;

    @Before
    public void before() {
        cardService = new CardService(cardDao, itemDao);
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
    public void createCard_createsCard() throws Exception {
        ArgumentCaptor<CardEntity> captor = ArgumentCaptor.forClass(CardEntity.class);

        ItemEntity itemEntity = new ItemEntity();

        when(itemDao.findById(itemEntity.getId())).thenReturn(Optional.of(itemEntity));

        cardService.createCard(itemEntity.getId());

        verify(cardDao).save(captor.capture());

        assertThat(captor.getValue().getItemEntity().getId()).isEqualTo(itemEntity.getId());
    }
}
