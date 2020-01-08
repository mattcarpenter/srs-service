package net.mattcarpenter.benkyou.srsservice.dao;

import net.mattcarpenter.benkyou.srsservice.entity.Field;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FieldDAO extends CrudRepository<Field, UUID> {}
