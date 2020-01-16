package net.mattcarpenter.benkyou.srsservice.mapper;

import net.mattcarpenter.benkyou.srsservice.entity.DeckEntity;
import net.mattcarpenter.benkyou.srsservice.model.DeckModel;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EntityToModelMapperTests {

    @Test
    public void mapToDeckModel_ok() {
        DeckEntity entity = new DeckEntity();
        entity.setTitle("foo");
        DeckModel model = EntityToModelMapper.mapToDeckModel(entity);
        assertThat(model.getTitle()).isEqualTo("foo");
    }
}
