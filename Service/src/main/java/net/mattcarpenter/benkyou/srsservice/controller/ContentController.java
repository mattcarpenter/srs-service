package net.mattcarpenter.benkyou.srsservice.controller;

import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;
import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutEntity;
import net.mattcarpenter.benkyou.srsservice.mapper.EntityToModelMapper;
import net.mattcarpenter.benkyou.srsservice.model.*;
import net.mattcarpenter.benkyou.srsservice.service.CardService;
import net.mattcarpenter.benkyou.srsservice.service.DeckService;
import net.mattcarpenter.benkyou.srsservice.service.LayoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/content")
public class ContentController {

    private DeckService deckService;
    private CardService cardService;
    private LayoutService layoutService;

    public ContentController(DeckService deckService, CardService cardService, LayoutService layoutService) {
        this.deckService = deckService;
        this.cardService = cardService;
        this.layoutService = layoutService;
    }

    @GetMapping("/cards")
    public AllCardsResponse getAllCards() {
        return new AllCardsResponse(cardService.getAllCards());
    }

    @GetMapping("/cards/{id}")
    public CardEntity getCard(@PathVariable UUID id) {
        return cardService.getCard(id);
    }

    @DeleteMapping("/cards/{id}")
    public void deleteCard(@PathVariable UUID id) {
        cardService.deleteCard(id);
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
    public DeckEntity getDeck(@PathVariable UUID id) {
        return deckService.getDeck(id);
    }

    @DeleteMapping("/decks/{id}")
    public void deleteDeck(@PathVariable UUID id) {
        deckService.deleteDeck(id);
    }

    @PostMapping("/decks/{id}/cards")
    public DeckEntity addCardsToDeck(@PathVariable UUID id, @RequestBody AddCardsToDeckRequest addCardsToDeckRequest) {
        deckService.addCardsToDeck(id, addCardsToDeckRequest.getCardIds());
        return deckService.getDeck(id);
    }

    @DeleteMapping("/decks/{id}/cards/{id}")
    public DeckEntity removeCardFromDeck(@PathVariable UUID deckId, @PathVariable UUID cardId) {
        return deckService.removeCardFromDeck(deckId, cardId);
    }

    @PostMapping("/layouts/")
    public LayoutEntity createLayout(@RequestBody CreateLayoutRequest createLayoutRequest) {
        return layoutService.createLayout(
                createLayoutRequest.getName(),
                createLayoutRequest.getFrontHtml(),
                createLayoutRequest.getBackHtml(),
                createLayoutRequest.getFrontCss(),
                createLayoutRequest.getBackCss(),
                createLayoutRequest.getFields()
        );
    }

    @GetMapping("/layouts/{id}")
    public LayoutEntity getLayout(@PathVariable UUID id) {
        return layoutService.getLayout(id);
    }

    @PostMapping("/layouts/{layoutId}/fields/")
    public LayoutEntity createFieldOnLayout(@PathVariable UUID layoutId, @RequestBody LayoutFieldModel field) {
        return layoutService.createFieldOnLayout(layoutId, field.getName());
    }

    @DeleteMapping("/layouts/{layoutId}/fields/{fieldName}")
    public LayoutEntity removeFieldFromLayout(@PathVariable UUID layoutId, @PathVariable String fieldName) {
        return layoutService.deleteField(layoutId, fieldName);
    }

    @DeleteMapping("/layouts/{id}")
    public void deleteLayout(@PathVariable UUID id) {
        layoutService.deleteLayout(id);
    }
}