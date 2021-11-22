package com.cepheid.cloud.skel;

import com.cepheid.cloud.skel.controller.ItemController;
import com.cepheid.cloud.skel.model.Description;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.DescriptionRepository;
import com.cepheid.cloud.skel.repository.ItemRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.beans.Statement;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Stream;

@SpringBootApplication(scanBasePackageClasses = {ItemController.class, SkelApplication.class})
@EnableJpaRepositories(basePackageClasses = {ItemRepository.class})
public class SkelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkelApplication.class, args);
    }

    @Bean
    ApplicationRunner initItems(ItemRepository itemRepository, DescriptionRepository descriptionRepository) {
        return args -> {
            Stream.of("Lord of the rings:undefined:My precious awesomeness:Review",
                    "Hobbit:valid:Boring:Review",
                    "Silmarillion:invalid:Cant sleep anymore :Blog",
                    "Unfinished Tales and The History of Middle-earth:undefined:Thrilling:Blog")
                    .forEach(string -> {
                        StringTokenizer tokenizer = new StringTokenizer(string, ":");
                        if (tokenizer.hasMoreElements()) {
                            Item item = new Item(tokenizer.nextToken(), tokenizer.nextToken());
                            Description description = new Description(tokenizer.nextToken(), tokenizer.nextToken(), item);
                            item.setDescriptions(Collections.singleton(description));
                            itemRepository.save(item);
                        }

                    });

            itemRepository.findAll().forEach(System.out::println);
        };
    }

}
