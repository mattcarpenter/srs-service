package net.mattcarpenter.benkyou.srsservice.dao;

import net.mattcarpenter.benkyou.srsservice.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardDAO extends CrudRepository<Card, UUID> {}
