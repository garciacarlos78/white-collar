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
    CommandLineRunner initDatabaseStores(StoreRepository storeRepository, PaintingRepository paintingRepository) {
        return args -> {
            log.info("Preloading " + storeRepository.save(new Store("Store 1", 10)));
            //log.info("Preloading " + storeRepository.save(new Store("Store 2", 15, paintingRepository.save(new Painting("Painting 1", "Carapaggio"))))));
            // Add some paintings to store id 1
            log.info("Preloading " + storeRepository.save(new Store("Store 2", 60)));
            log.info("Preloading " + storeRepository.save(new Store("Store 3", 5)));
            log.info("Preloading " + storeRepository.save(new Store("Store 4", 20)));
            log.info("Preloading " + storeRepository.save(new Store("Store 5", 30)));

            log.info("Preloading " + paintingRepository.save(new Painting("Painter 1", "Painting name 1", 50, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painting name 2", "Painting name 2", 450, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 3", "Painting name 3", 500, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 4", "Painting name 4", 2.50, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 1", "Painting name 5", 30.50, new Date())));

            log.info("Preloading " + storeRepository.save(new Store("Store 6", 30, paintingRepository.findAll())));


            // log.info(("Preloaded: " + storeRepository.findAll()));
            // Introduce some paintings in store 1, to test get rest
            /*Store store = storeRepository.getOne(1L);
            if (store==null) {
                log.info("Store null");
            } else {
                log.info("Store not null");

                store.addPainting(new Painting("null", "La tentación anónima", 50, new Date()));
            }*/
            /*storeRepository.getOne(1L).addPainting(new Painting("null", "La tentación anónima", 50, new Date()));
            storeRepository.getOne(1L).addPainting(new Painting("Carapaggio", "La tentación de Carapaggio", 150, new Date()));
            storeRepository.getOne(1L).addPainting(new Painting("Carapó", "La tentación de Almeida", 200, new Date()));*/
        };

    }

    // Initialize a Painting database
    // TODO: (to ask): a repository for the whole application or a repository for each store?
/*
    @Bean
    CommandLineRunner initDatabasePaintings(PaintingRepository paintingRepository) {
        return args -> {
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 1", "Painting name 1", 50, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painting name 2", "Painting name 2", 450, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 3", "Painting name 3", 500, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 4", "Painting name 4", 2.50, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painter 1", "Painting name 5", 30.50, new Date())));
        };
    }
*/
}
