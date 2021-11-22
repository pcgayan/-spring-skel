package com.cepheid.cloud.skel.controller;

import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Component
@RestController
@CrossOrigin
@Singleton
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // curl -X GET "http://localhost:9443/api/2.0/items/find"
    @RequestMapping(value = "/api/2.0/items/find", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    Collection<Item> getItemIdById() {
        return itemService.getAllItems();
    }

    // curl -X GET "http://localhost:9443/api/2.0/items/find/4"
    @RequestMapping(value = "/api/2.0/items/find/{id}", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    Item getItemIdById(@PathVariable(name = "id", required = false) Long id) {
        return itemService.getASingleItem(id);
    }

    // curl -X GET "http://localhost:9443/api/2.0/items/findByTitle/Hobbit"
    @RequestMapping(value = "/api/2.0/items/findByTitle/{title}", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    Item getItemIdById(@PathVariable(name = "title") String title) {
        return itemService.getASingleItemByTitle(title);
    }

    // curl -X PUT -v "http://localhost:9443/api/2.0/items/add" -H 'Content-type:application/json' -d '{"title": "007", "state": "valid"}'
    @RequestMapping(value = "/api/2.0/items/add", method = RequestMethod.PUT)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addItem(@RequestBody Item item) {
        try {
            itemService.add(item);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // curl -X DELETE "http://localhost:9443/api/2.0/items/delete/4"
    @RequestMapping(value = "/api/2.0/items/delete/{id}", method = RequestMethod.DELETE)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> deleteItemById(@PathVariable(name = "id") Long id) {
        try {
            itemService.deleteItem(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}