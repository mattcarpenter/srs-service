package net.mattcarpenter.benkyou.srsservice.controller.content;

import net.mattcarpenter.benkyou.srsservice.entity.Deck;
import net.mattcarpenter.benkyou.srsservice.entity.Item;
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

    public ContentController(ItemService itemService, DeckService deckService) {
        this.itemService = itemService;
        this.deckService = deckService;
    }

    @GetMapping("/item/{id}")
    public Item getItemById(@PathVariable String id) {
        return itemService.getItem(UUID.fromString(id));
    }

    @GetMapping("/item")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/item")
    public String createItem(@RequestBody Item item) {
        itemService.createItem(item);
        return "todo";
    }

    @GetMapping("/deck/{id}")
    public Deck getDeck(@PathVariable String id) {
        return deckService.getDeck(UUID.fromString(id));
    }
}