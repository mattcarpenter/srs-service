package net.mattcarpenter.benkyou.srsservice.service;

import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
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
    private CardService cardService;

    @Before
    public void before() {
        cardService = new CardService(cardDao);
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
        ArgumentCaptor<CardEntity> captor = ArgumentCaptor.forClass(CardEntity.class);
        cardService.createCard();
        verify(cardDao).save(captor.capture());
        // todo: assertion
    }
}
