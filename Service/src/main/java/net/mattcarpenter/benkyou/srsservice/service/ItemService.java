package net.mattcarpenter.benkyou.srsservice.service;

import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.entity.Item;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemService {

    private ItemDao itemDao;

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public Item getItem(UUID id) {
        return itemDao.findById(id).orElseThrow();
    }
}
