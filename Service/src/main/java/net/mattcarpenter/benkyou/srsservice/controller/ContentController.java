package net.mattcarpenter.benkyou.srsservice.controller;

import net.mattcarpenter.benkyou.srsservice.entity.Item;
import net.mattcarpenter.benkyou.srsservice.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/content")
public class ContentController {

    private ItemService itemService;

    public ContentController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/item/{id}")
    public Item getItemById(HttpServletRequest request, @PathVariable String id) {
        return itemService.getItem(UUID.fromString(id));
    }
}