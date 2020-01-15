package net.mattcarpenter.benkyou.srsservice.service;

import net.mattcarpenter.benkyou.srsservice.dao.CardDao;
import net.mattcarpenter.benkyou.srsservice.dao.DeckDao;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    public void deleteDeck_ok() {
        UUID deckId = UUID.randomUUID();
        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);
        deckService.deleteDeck(deckId);
        verify(deckDao).deleteById(captor.capture());
        assertThat(captor.getValue().equals(deckId));
    }

    @Test
    public void removeCardFromDeck_ok() {
        DeckEntity deck = new DeckEntity();
        CardEntity card = new CardEntity();
        deck.addCard(card);
        assertThat(deck.getCardEntities().size()).isEqualTo(1);
        when(deckDao.findById(deck.getId())).thenReturn(Optional.of(deck));
        ArgumentCaptor<DeckEntity> captor = ArgumentCaptor.forClass(DeckEntity.class);
        deckService.removeCardFromDeck(deck.getId(), card.getId());
        verify(deckDao).save(captor.capture());
        assertThat(captor.getValue().getCardEntities().size()).isEqualTo(0);
    }

    @Test
    public void removeCardFromDeck_throwsWhenCardInvalid() {
        DeckEntity deck = new DeckEntity();
        CardEntity card = new CardEntity();
        when(deckDao.findById(deck.getId())).thenReturn(Optional.of(deck));
        assertThatThrownBy(() -> deckService.removeCardFromDeck(deck.getId(), card.getId()));
    }
}

