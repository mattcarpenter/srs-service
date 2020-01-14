package net.mattcarpenter.benkyou.srsservice.controller;

import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import net.mattcarpenter.benkyou.srsservice.mapper.EntityToModelMapper;
import net.mattcarpenter.benkyou.srsservice.model.*;
import net.mattcarpenter.benkyou.srsservice.service.CardService;
import net.mattcarpenter.benkyou.srsservice.service.DeckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/content")
public class ContentController {

    private DeckService deckService;
    private CardService cardService;

    public ContentController(DeckService deckService, CardService cardService) {
        this.deckService = deckService;
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public AllCardsResponse getAllCards() {
        return new AllCardsResponse(cardService.getAllCards());
    }

    @GetMapping("/cards/{id}")
    public CardEntity getCard(@PathVariable String id) {
        return cardService.getCard(UUID.fromString(id));
    }

    @PostMapping("/cards")
    public CardEntity createCard(@RequestBody CreateCardRequest createCardRequest) {
        return cardService.createCard(createCardRequest.getLayoutId(), createCardRequest.getFields());
    }

    @GetMapping("/decks")
    public AllDecksResponse getAllDecks() {
        List<DeckModel> decks = deckService.getAllDecks()
                .stream()
                .map(EntityToModelMapper::mapToDeckModel)
                .collect(Collectors.toList());

        return new AllDecksResponse(decks);
    }

    @PostMapping("/decks")
    public DeckEntity createDeck(@RequestBody CreateDeckRequest createDeckRequest) {
        return deckService.createDeck(createDeckRequest.getTitle());
    }

    @GetMapping("/decks/{id}")
    public DeckEntity getDeck(@PathVariable String id) {
        return deckService.getDeck(UUID.fromString(id));
    }

    @PostMapping("/decks/{id}/cards")
    public DeckEntity addCardsToDeck(@PathVariable String id, @RequestBody AddCardsToDeckRequest addCardsToDeckRequest) {
        deckService.addCardsToDeck(UUID.fromString(id), addCardsToDeckRequest.getCardIds());
        return deckService.getDeck(UUID.fromString(id));
    }
}