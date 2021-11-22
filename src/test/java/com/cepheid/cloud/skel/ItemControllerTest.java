package com.cepheid.cloud.skel;

import com.cepheid.cloud.skel.model.Item;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ItemControllerTest extends TestBase {

    @Test
    public void testGetAllItems() {
        itemController = getBuilder("/api/2.0/items/find");

        Collection<Item> items = itemController.get(new GenericType<>() {
        });

        assertThat(items.size()).isEqualTo(4);
        for (Item item : items) {
            assertThat(item.getDescriptions()).isNotNull();
        }
    }

    @Test
    public void testGetItemById() {
        itemController = getBuilder("/api/2.0/items/find/1");

        Item item = itemController.get(new GenericType<>() {
        });

        assertThat(item).isNotNull();
        assertThat(item.getDescriptions()).isNotNull();
    }

    @Test
    public void testGetItemByTitle() {
        itemController = getBuilder("/api/2.0/items/findByTitle/Hobbit");

        Item item = itemController.get(new GenericType<>() {
        });

        assertThat(item).isNotNull();
        assertThat(item.getTitle()).isEqualTo("Hobbit");
        assertThat(item.getDescriptions()).isNotNull();
    }

    @Test
    public void testGetItemByIdFail() {
        itemController = getBuilder("/api/2.0/items/find/99");

        Response response = itemController.get();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Ignore
    @Test
    public void testDeleteItemById() {
        itemController = getBuilder("/api/2.0/items/delete/1");

        ResponseEntity<?> response = itemController.get(new GenericType<>() {
        });
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED.value());

    }

    @Ignore
    @Test
    public void testPutItem() {
        // curl -X PUT -v "http://localhost:9443/api/2.0/items/add" -H 'Content-type:application/json' -d '{"title": "007", "state": "valid"}'
        itemController = getBuilder("/api/2.0/items/add", "Content-type:application/json",
                "{\"title\": \"007\", \"state\": \"valid\"}");

        ResponseEntity<?> response = itemController.get(new GenericType<>() {
        });
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED.value());

    }
}
