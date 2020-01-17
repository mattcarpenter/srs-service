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
        return new AllCardsResponse(
                cardService.getAllCards().stream().map(EntityToModelMapper::mapToCardModel).collect(Collectors.toList())
        );
    }

    @GetMapping("/cards/{cardId}")
    public CardModel getCard(@PathVariable UUID cardId) {
        return EntityToModelMapper.mapToCardModel(cardService.getCard(cardId));
    }

    @DeleteMapping("/cards/{cardId}")
    public void deleteCard(@PathVariable UUID cardId) {
        cardService.deleteCard(cardId);
    }

    @PostMapping("/cards")
    public CardModel createCard(@RequestBody CreateCardRequest createCardRequest) {
        return EntityToModelMapper.mapToCardModel(
                cardService.createCard(createCardRequest.getLayoutId(), createCardRequest.getFields())
        );
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

    @GetMapping("/decks/{deckId}")
    public DeckEntity getDeck(@PathVariable UUID deckId) {
        return deckService.getDeck(deckId);
    }

    @DeleteMapping("/decks/{deckId}")
    public void deleteDeck(@PathVariable UUID deckId) {
        deckService.deleteDeck(deckId);
    }

    @PostMapping("/decks/{deckId}/cards")
    public DeckEntity addCardsToDeck(@PathVariable UUID deckId, @RequestBody AddCardsToDeckRequest addCardsToDeckRequest) {
        deckService.addCardsToDeck(deckId, addCardsToDeckRequest.getCardIds());
        return deckService.getDeck(deckId);
    }

    @DeleteMapping("/decks/{deckId}/cards/{cardId}")
    public DeckEntity removeCardFromDeck(@PathVariable UUID deckId, @PathVariable UUID cardId) {
        return deckService.removeCardFromDeck(deckId, cardId);
    }

    @GetMapping("/layouts")
    public AllLayoutsResponse getAllLayouts() {
        return new AllLayoutsResponse(layoutService.getAllLayouts());
    }

    @PostMapping("/layouts")
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

    @GetMapping("/layouts/{layoutId}")
    public LayoutEntity getLayout(@PathVariable UUID layoutId) {
        return layoutService.getLayout(layoutId);
    }

    @PostMapping("/layouts/{layoutId}/fields/")
    public LayoutEntity createFieldOnLayout(@PathVariable UUID layoutId, @RequestBody LayoutFieldModel field) {
        return layoutService.createFieldOnLayout(layoutId, field.getName());
    }

    @DeleteMapping("/layouts/{layoutId}/fields/{fieldName}")
    public LayoutEntity removeFieldFromLayout(@PathVariable UUID layoutId, @PathVariable String fieldName) {
        return layoutService.deleteField(layoutId, fieldName);
    }

    @DeleteMapping("/layouts/{layoutId}")
    public void deleteLayout(@PathVariable UUID layoutId) {
        layoutService.deleteLayout(layoutId);
    }
}