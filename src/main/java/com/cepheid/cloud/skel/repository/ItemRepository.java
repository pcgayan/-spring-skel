package com.cepheid.cloud.skel.repository;

import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByTitle(String title);

    List<Item> findByState(State state);

}
