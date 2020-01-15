package net.mattcarpenter.benkyou.srsservice.mapper;

import net.mattcarpenter.benkyou.srsservice.entity.LayoutFieldEntity;
import net.mattcarpenter.benkyou.srsservice.model.LayoutFieldModel;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ModelToEntityMapperTests {

    @Test
    public void mapToLayoutEntityField_ok() {
        LayoutFieldModel model = new LayoutFieldModel();
        model.setName("foo");
        LayoutFieldEntity entity = ModelToEntityMapper.mapToLayoutFieldEntity(model);
        assertThat(entity.getName()).isEqualTo("foo");
    }
}
