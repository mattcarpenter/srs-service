package net.mattcarpenter.benkyou.srsservice.dao;

import net.mattcarpenter.benkyou.srsservice.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemDAO extends CrudRepository<Item, UUID> {}
