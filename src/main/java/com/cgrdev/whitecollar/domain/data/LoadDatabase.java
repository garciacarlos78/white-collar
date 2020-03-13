package com.cgrdev.whitecollar.domain.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@Slf4j
public class LoadDatabase {

    // Initialize the Painting repository and the Store repository, adding some paintings, stores, and paintings to the stores
    @Bean
    CommandLineRunner initDatabaseStores(StoreRepository storeRepository, PaintingRepository paintingRepository) {
        return args -> {

            // Create paintings for store 1, and create store 1 adding the paintings
            log.info("Preloading " + paintingRepository.save(new Painting("Painting 1", "Painter 1", 45, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painting 2", "Painter 2", 450, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painting 3", "Painter 3", 50, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painting 4", "Painter 4", 5000, new Date())));
            log.info("Preloading " + paintingRepository.save(new Painting("Painting 5", "Painter 5", 0.99, new Date())));

            log.info("Preloading " + storeRepository.save(new Store("Store 1", 60, paintingRepository.findAll())));

            // Create paintings for store 2, and create store 2 adding the paintings
            List<Painting> paintings = new ArrayList<>();
            paintings.add(paintingRepository.save(new Painting("Painting 6", "Painter 1", 41.99, new Date())));
            paintings.add(paintingRepository.save(new Painting("Painting 7", 450, new Date())));
            paintings.add(paintingRepository.save(new Painting("Painting 8", "Painter 3", 4500, new Date())));
            paintings.add(paintingRepository.save(new Painting("Painting 9", "Painter 5", 0.99, new Date())));

            log.info("Preloading " + storeRepository.save(new Store("Store 2", 10, paintings)));

            // Create paintings for store 3, and create store 3 adding the paintings
            paintings.clear();
            paintings.add(paintingRepository.save(new Painting("Painting 10", "Painter 1", 234.41, new Date())));
            paintings.add(paintingRepository.save(new Painting("Painting 11", 4500, new Date())));
            paintings.add(paintingRepository.save(new Painting("Painting 12", "Painter 3", 44999.99, new Date())));
            paintings.add(paintingRepository.save(new Painting("Painting 13", "Painter 5", 0.49, new Date())));

            log.info("Preloading " + storeRepository.save(new Store("Store 3", 45, paintings)));

            // Create paintings for store 4, and create store 4 adding the paintings
            paintings.clear();
            paintings.add(paintingRepository.save(new Painting("Painting 14", "Painter 1", 234.41, new Date())));
            paintings.add(paintingRepository.save(new Painting("Painting 15", 4500, new Date())));
            paintings.add(paintingRepository.save(new Painting("Painting 16", "Painter 3", 44999.99, new Date())));
            paintings.add(paintingRepository.save(new Painting("Painting 17", "Painter 5", 0.49, new Date())));

            log.info("Preloading " + storeRepository.save(new Store("Store 4", 6, paintings)));
        };
    }
}