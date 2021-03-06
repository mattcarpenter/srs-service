package net.mattcarpenter.benkyou.srsservice.dao;

import net.mattcarpenter.benkyou.srsservice.entity.LayoutFieldEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LayoutFieldDao extends CrudRepository<LayoutFieldEntity, UUID> {}
