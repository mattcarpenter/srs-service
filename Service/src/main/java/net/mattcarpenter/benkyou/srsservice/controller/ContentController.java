package net.mattcarpenter.benkyou.srsservice.controller;

import net.mattcarpenter.benkyou.srsservice.entity.Card;
import net.mattcarpenter.benkyou.srsservice.entity.Deck;
import net.mattcarpenter.benkyou.srsservice.entity.Item;
import net.mattcarpenter.benkyou.srsservice.model.AddCardsToDeckRequest;
import net.mattcarpenter.benkyou.srsservice.model.CreateCardRequest;
import net.mattcarpenter.benkyou.srsservice.service.CardService;
import net.mattcarpenter.benkyou.srsservice.service.DeckService;
import net.mattcarpenter.benkyou.srsservice.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/content")
public class ContentController {

    private ItemService itemService;
    private DeckService deckService;
    private CardService cardService;

    public ContentController(ItemService itemService, DeckService deckService, CardService cardService) {
        this.itemService = itemService;
        this.deckService = deckService;
        this.cardService = cardService;
    }

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/items/{id}")
    public Item getItemById(@PathVariable String id) {
        return itemService.getItem(UUID.fromString(id));
    }

    @PostMapping("/items")
    public Item createItem(@RequestBody Item item) {
        itemService.createItem(item);
        return item;
    }

    @GetMapping("/cards/{id}")
    public Card getCard(@PathVariable String id) {
        return cardService.getCard(UUID.fromString(id));
    }

    @PostMapping("/cards")
    public Card createCard(@RequestBody CreateCardRequest createCardRequest) {
        return cardService.createCard(createCardRequest.getItemId(), createCardRequest.getFrontFieldId(),
                createCardRequest.getBackFieldId());
    }

    @GetMapping("/decks")
    public List<Deck> getAllDecks() {
        return deckService.getAllDecks();
    }

    @GetMapping("/decks/{id}")
    public Deck getDeck(@PathVariable String id) {
        return deckService.getDeck(UUID.fromString(id));
    }

    @PostMapping("/decks/{id}/cards")
    public void addCardsToDeck(@PathVariable String id, @RequestBody AddCardsToDeckRequest addCardsToDeckRequest) {
        deckService.addCardsToDeck(UUID.fromString(id), addCardsToDeckRequest.getCardIds());
    }
}