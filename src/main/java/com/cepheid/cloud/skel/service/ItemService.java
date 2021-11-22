package com.cepheid.cloud.skel.service;


import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.DescriptionRepository;
import com.cepheid.cloud.skel.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    DescriptionRepository descriptionRepository;


    public Item getASingleItem(Long id) throws IllegalArgumentException {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item not found"));
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
        if (itemRepository.findById(item.getId()).isPresent()) {
            throw new IllegalArgumentException("Item already found, nothing to insert");
        } else {
            itemRepository.save(item);
        }
    }

    public void update(Item item) {
        if (itemRepository.findById(item.getId()).isPresent()) {
            itemRepository.save(item);
        } else {
            throw new IllegalArgumentException("Item not found, nothing to update");

        }
    }
}