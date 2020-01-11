package net.mattcarpenter.benkyou.srsservice.service;

import com.google.common.collect.Lists;
import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.DeckDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeckServiceTests {
    private DeckDao deckDao = Mockito.mock(DeckDao.class);
    private CardDao cardDao = Mockito.mock(CardDao.class);

    private DeckService deckService;

    @Before
    public void before() {
        deckService = new DeckService(deckDao, cardDao);
    }

    @Test
    public void getDeck_getsDeck() {
        DeckEntity deck = new DeckEntity();
        when(deckDao.findById(deck.getId())).thenReturn(Optional.of(deck));
        assertThat(deckService.getDeck(deck.getId())).isEqualTo(deck);
    }

    @Test
    public void getAllDecks_getsDecks() {
        DeckEntity deck = new DeckEntity();
        when(deckDao.findAll()).thenReturn(Collections.singletonList(deck));
        assertThat(deckService.getAllDecks()).contains(deck);
    }

    @Test
    public void addCardsToDeck_ok() {
        ArgumentCaptor<DeckEntity> captor = ArgumentCaptor.forClass(DeckEntity.class);

        CardEntity card1 = new CardEntity();
        CardEntity card2 = new CardEntity();
        DeckEntity deck = new DeckEntity();

        // needed otherwise the Deck's HashSet for cards will consider card 1 and 2 the same
        card1.setCreatedBy(UUID.randomUUID());
        card2.setCreatedBy(UUID.randomUUID());

        when(deckDao.findById(deck.getId())).thenReturn(Optional.of(deck));
        when(cardDao.findById(card1.getId())).thenReturn(Optional.of(card1));
        when(cardDao.findById(card2.getId())).thenReturn(Optional.of(card2));

        deckService.addCardsToDeck(deck.getId(), Arrays.asList(card1.getId(), card2.getId()));

        verify(deckDao).save(captor.capture());

        assertThat(captor.getValue().getId()).isEqualTo(deck.getId());
        assertThat(captor.getValue().getCardEntities().size()).isEqualTo(2);
    }

    @Test
    public void createDeck_ok() {
        ArgumentCaptor<DeckEntity> captor = ArgumentCaptor.forClass(DeckEntity.class);
        deckService.createDeck("Test deck");
        verify(deckDao).save(captor.capture());
        assertThat(captor.getValue().getTitle()).isEqualTo("Test deck");
    }
}

