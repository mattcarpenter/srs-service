package net.mattcarpenter.benkyou.srsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mattcarpenter.benkyou.srsservice.dao.FieldDao;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
import net.mattcarpenter.benkyou.srsservice.entity.FieldEntity;
import net.mattcarpenter.benkyou.srsservice.entity.ItemEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemServiceTests {

    private List<FieldEntity> fieldEntities;
    private ItemDao itemDao = Mockito.mock(ItemDao.class);
    private FieldDao fieldDao = Mockito.mock(FieldDao.class);
    private ItemService itemService;

    @Before
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        fieldEntities = new ArrayList<>();
        fieldEntities.add(new FieldEntity(mapper.readTree("{\"foo\":\"bar\"}"),UUID.randomUUID()));
        itemService = new ItemService(itemDao, fieldDao);
    }

    @Test
    public void getAllItems_returnsItems() {
        ItemEntity item = new ItemEntity(UUID.randomUUID(), fieldEntities);
        when(itemDao.findAll()).thenReturn(Collections.singletonList(item));
        assertThat(itemService.getAllItems()).containsExactly(item);
    }

    @Test
    public void getItem_returnsItem() {
        UUID id = UUID.randomUUID();
        ItemEntity item = new ItemEntity(UUID.randomUUID(), fieldEntities);
        when(itemDao.findById(id)).thenReturn(Optional.of(item));
        assertThat(itemService.getItem(id)).isEqualTo(item);
    }

    @Test
    public void createItem_createsItem() {
        ItemEntity item = new ItemEntity(UUID.randomUUID(), fieldEntities);
        itemService.createItem(item);
        verify(itemDao, times(1)).save(item);
    }
}
