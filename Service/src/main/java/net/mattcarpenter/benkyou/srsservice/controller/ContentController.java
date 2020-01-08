package net.mattcarpenter.benkyou.srsservice.controller;

import net.mattcarpenter.benkyou.srsservice.entity.Item;
import net.mattcarpenter.benkyou.srsservice.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/content")
public class ContentController {

    private ItemService itemService;

    public ContentController(ItemService itemService) {
        this.itemService = itemService;
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
}