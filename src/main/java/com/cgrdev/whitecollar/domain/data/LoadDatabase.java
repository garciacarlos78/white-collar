package com.cgrdev.whitecollar.domain.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@Slf4j
public class LoadDatabase {

    // Initialize a Store database with 5 stores
    @Bean
    CommandLineRunner initDatabaseStores(StoreRepository storeRepository) {
        return args -> {
            log.info("Preloading " + storeRepository.save(new Store("Store 1", 10)));
            log.info("Preloading " + storeRepository.save(new Store("Store 2", 15)));
            log.info("Preloading " + storeRepository.save(new Store("Store 3", 5)));
            log.info("Preloading " + storeRepository.save(new Store("Store 4", 20)));
            log.info("Preloading " + storeRepository.save(new Store("Store 5", 30)));
        };
    }

    // Initialize a Painting database
    // TODO: (to ask): a repository for the whole application or a repository for each store?
    @Bean
    CommandLineRunner initDatabasePaintings(PaintingRepository paintingRepository) {
        return args -> {
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 1", "Painting name 1", 50, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting(null, "Painting name 2", 450, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 3", "Painting name 3", 500, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 4", "Painting name 4", 2.50, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 1", "Painting name 5", 30.50, new Date())));
        };
    }
}
