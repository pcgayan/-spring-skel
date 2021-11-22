package com.cepheid.cloud.skel.repository;

import com.cepheid.cloud.skel.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByTitle(String title);
}
