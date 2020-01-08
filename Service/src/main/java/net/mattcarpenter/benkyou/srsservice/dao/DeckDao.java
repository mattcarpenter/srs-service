package net.mattcarpenter.benkyou.srsservice.dao;

import net.mattcarpenter.benkyou.srsservice.entity.Deck;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DeckDao extends CrudRepository<Deck, UUID> {}
