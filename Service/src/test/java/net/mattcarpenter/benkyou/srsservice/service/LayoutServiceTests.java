package net.mattcarpenter.benkyou.srsservice.service;

import ch.qos.logback.core.Layout;
import net.mattcarpenter.benkyou.srsservice.dao.LayoutDao;
import net.mattcarpenter.benkyou.srsservice.dao.LayoutFieldDao;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutEntity;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutFieldEntity;
import net.mattcarpenter.benkyou.srsservice.model.LayoutFieldModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LayoutServiceTests {

    private LayoutService layoutService;
    private LayoutDao layoutDao = Mockito.mock(LayoutDao.class);
    private LayoutFieldDao layoutFieldDao = Mockito.mock(LayoutFieldDao.class);

    @Before
    public void before() {
        layoutService = new LayoutService(layoutDao);
    }

    @Test
    public void getLayout_ok() {
        LayoutEntity layout = new LayoutEntity();
        when(layoutDao.findById(layout.getId())).thenReturn(Optional.of(layout));
        assertThat(layoutService.getLayout(layout.getId())).isEqualTo(layout);
    }

    @Test
    public void createLayout_ok() {
        LayoutFieldModel field = new LayoutFieldModel();
        field.setName("foo");
        ArgumentCaptor<LayoutEntity> captor = ArgumentCaptor.forClass(LayoutEntity.class);
        layoutService.createLayout("test layout", "frontHtml", "backHtml", "frontCss",
                "backCss", Collections.singletonList(field));

        verify(layoutDao).save(captor.capture());
        LayoutEntity layout = captor.getValue();

        assertThat(layout.getName()).isEqualTo("test layout");
        assertThat(layout.getFrontHtml()).isEqualTo("frontHtml");
        assertThat(layout.getBackHtml()).isEqualTo("backHtml");
        assertThat(layout.getFrontCss()).isEqualTo("frontCss");
        assertThat(layout.getBackCss()).isEqualTo("backCss");
        assertThat(layout.getFields().stream().anyMatch(f -> f.getName().equals("foo")));
        assertThat(layout.getFields().size()).isEqualTo(1);
    }

    @Test
    public void deleteLayout_ok() {
        UUID layoutId = UUID.randomUUID();
        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);
        layoutService.deleteLayout(layoutId);
        verify(layoutDao).deleteById(captor.capture());
        assertThat(captor.getValue()).isEqualTo(layoutId);
    }

    @Test
    public void getAllLayouts_ok() {
        LayoutEntity layout = new LayoutEntity();
        when(layoutDao.findAll()).thenReturn(Collections.singletonList(layout));
        assertThat(layoutService.getAllLayouts()).containsExactly(layout);
    }

    @Test
    public void createFieldOnLayout_ok() {
        LayoutEntity layout = new LayoutEntity();
        when(layoutDao.findById(layout.getId())).thenReturn(Optional.of(layout));
        ArgumentCaptor<LayoutEntity> captor = ArgumentCaptor.forClass(LayoutEntity.class);
        layoutService.createFieldOnLayout(layout.getId(), "foo");
        verify(layoutDao).save(captor.capture());
        assertThat(captor.getValue().getFields().size()).isEqualTo(1);
    }

    @Test
    public void deleteField_ok() {
        LayoutEntity layout = new LayoutEntity();
        LayoutFieldEntity field = new LayoutFieldEntity();
        field.setName("foo");
        layout.addField(field);
        assertThat(layout.getFields().size()).isEqualTo(1);
        when(layoutDao.findById(layout.getId())).thenReturn(Optional.of(layout));
        ArgumentCaptor<LayoutEntity> captor = ArgumentCaptor.forClass(LayoutEntity.class);
        layoutService.deleteField(layout.getId(), "foo");
        verify(layoutDao).save(captor.capture());
        assertThat(captor.getValue().getFields().size()).isEqualTo(0);
    }
}
