package com.cepheid.cloud.skel;

import com.cepheid.cloud.skel.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.GenericType;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ItemControllerTest extends TestBase {

    @Test
    public void testGetAllItems() {
        itemController = getBuilder("/app/api/1.0/items");

        Collection<Item> items = itemController.get(new GenericType<>() {
        });

        assertThat(items.size()).isEqualTo(4);
        for (Item item : items) {
            assertThat(item.getDescriptions()).isNotNull();
        }
    }

    @Test
    public void testGetOneItems() {
        itemController = getBuilder("/app/api/1.0/items");

        Collection<Item> items = itemController.get(new GenericType<>() {
        });

        assertThat(items.size()).isEqualTo(4);
        for (Item item : items) {
            assertThat(item.getDescriptions()).isNotNull();
        }
    }
}
