package net.mattcarpenter.benkyou.srsservice.dao;

import net.mattcarpenter.benkyou.srsservice.entity.FieldEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FieldDao extends CrudRepository<FieldEntity, UUID> {}
