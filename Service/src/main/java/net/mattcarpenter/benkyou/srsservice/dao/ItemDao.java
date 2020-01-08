package net.mattcarpenter.benkyou.srsservice.dao;

import net.mattcarpenter.benkyou.srsservice.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemDao extends CrudRepository<Item, UUID> {}
