package com.cepheid.cloud.skel.service;


import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.State;
import com.cepheid.cloud.skel.repository.DescriptionRepository;
import com.cepheid.cloud.skel.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.List;

@Component
@Service
@Singleton
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    Logger logger = LoggerFactory.getLogger(ItemService.class);


    public Item getASingleItem(Long id) throws IllegalArgumentException {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }

    public Item getASingleItemByTitle(String title) throws IllegalArgumentException {
        return itemRepository.findByTitle(title).orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }

    public List<Item> getASingleItemByState(State state) throws IllegalArgumentException {
        return itemRepository.findByState(state);
    }


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void deleteItem(Long id) throws IllegalArgumentException {
        if (itemRepository.findById(id).isPresent()) {
            itemRepository.deleteById(id);
        } else throw new IllegalArgumentException("Item deletion failed");
    }

    public void add(Item item) {
        if (item.getId() != null && itemRepository.findById(item.getId()).isPresent()) {
            logger.debug("update for item " + item.getTitle());
        } else {
            logger.debug("insert for item " + item.getTitle());
        }

        itemRepository.save(item);
    }

}