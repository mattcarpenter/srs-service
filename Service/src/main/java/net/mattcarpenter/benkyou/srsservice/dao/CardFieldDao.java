package net.mattcarpenter.benkyou.srsservice.dao;

import net.mattcarpenter.benkyou.srsservice.entity.CardFieldEntity;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardFieldDao extends CrudRepository<CardFieldEntity, UUID> {}
