package net.mattcarpenter.benkyou.srsservice.service;

import com.google.common.collect.Lists;
import net.mattcarpenter.benkyou.srsservice.dao.FieldDao;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.entity.ItemEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {

    private ItemDao itemDao;
    private FieldDao fieldDao;

    public ItemService(ItemDao itemDao, FieldDao fieldDao) {
        this.itemDao = itemDao;
        this.fieldDao = fieldDao;
    }

    public ItemEntity getItem(UUID id) {
        return itemDao.findById(id).orElseThrow();
    }

    public List<ItemEntity> getAllItems() {
        return Lists.newArrayList(itemDao.findAll());

    }

    public void createItem(ItemEntity itemEntity) {
        itemEntity.getFieldEntities().forEach(field -> fieldDao.save(field));
        itemDao.save(itemEntity);
    }
}
