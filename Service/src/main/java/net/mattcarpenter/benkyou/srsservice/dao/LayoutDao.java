package net.mattcarpenter.benkyou.srsservice.dao;

import net.mattcarpenter.benkyou.srsservice.entity.LayoutEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LayoutDao extends CrudRepository<LayoutEntity, UUID> {}
