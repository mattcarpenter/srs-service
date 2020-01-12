package net.mattcarpenter.benkyou.srsservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mattcarpenter.benkyou.srsservice.dao.ItemDao;
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

    private ItemDao itemDao = Mockito.mock(ItemDao.class);
    private ItemService itemService;
    private JsonNode data;

    @Before
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readTree("{\"foo\":\"bar\"}");
        itemService = new ItemService(itemDao);
    }

    @Test
    public void getAllItems_returnsItems() {
        ItemEntity item = new ItemEntity(UUID.randomUUID(), data);
        when(itemDao.findAll()).thenReturn(Collections.singletonList(item));
        assertThat(itemService.getAllItems()).containsExactly(item);
    }

    @Test
    public void getItem_returnsItem() {
        UUID id = UUID.randomUUID();
        ItemEntity item = new ItemEntity(UUID.randomUUID(), data);
        when(itemDao.findById(id)).thenReturn(Optional.of(item));
        assertThat(itemService.getItem(id)).isEqualTo(item);
    }

    @Test
    public void createItem_createsItem() {
        ItemEntity item = new ItemEntity(UUID.randomUUID(), data);
        itemService.createItem(item);
        verify(itemDao, times(1)).save(item);
    }
}
