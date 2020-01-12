package net.mattcarpenter.benkyou.srsservice.entity;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DeckEntityTests {

    @Test
    public void addCard_ok() {
        DeckEntity deck = new DeckEntity();
        CardEntity card1 = new CardEntity();
        CardEntity card2 = new CardEntity();
        deck.addCard(card1);
        deck.addCard(card2);
        assertThat(deck.getCardEntities().size()).isEqualTo(2);
    }
}
