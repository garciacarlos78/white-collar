package com.cgrdev.whitecollar.domain.controller;

import com.cgrdev.whitecollar.domain.data.Painting;
import com.cgrdev.whitecollar.domain.data.PaintingRepository;
import com.cgrdev.whitecollar.domain.data.Store;
import com.cgrdev.whitecollar.domain.data.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.decimal4j.util.DoubleRounder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final StoreRepository storeRepository;
    private final PaintingRepository paintingRepository;

    RestController(StoreRepository storeRepository, PaintingRepository paintingRepository) {
        this.storeRepository=storeRepository;
        this.paintingRepository = paintingRepository;
    }

    // Aggregate root

    // Get stores list
    @GetMapping("/shops/")
    List<Store> all() {
        return storeRepository.findAll();
    }

    // Introduce a new store
    @PostMapping("/shops/")
    Store newStore (@RequestBody Store newStore) {
        return storeRepository.save(newStore);
    }

    // Add painting to a store
    @PostMapping("/shops/{id}/pictures")
    void addPainting(@RequestBody Painting newPainting, @PathVariable Long id) {

        // Check if the given store id exists
        Store store = storeRepository.findById(id).orElseThrow(() ->
                new StoreNotFoundException(id));

        newPainting.setEntryDate(new Date());
        // By contract, we must provide just author and painting name
        // Also by contract, price is mandatory, so we assign each painting a random price from 0 to 1M€
        // Library DoubleRounder added to pom.xml to round price to just 2 decimals
        newPainting.setPrice(DoubleRounder.round(Math.random()*1000000, 2));

        store.getPaintings().add(paintingRepository.save(newPainting));

        // Necessary to persist the changes
        storeRepository.flush();
    }

    // List paintings of a store
    @GetMapping("/shops/{id}/pictures")
    List<Painting> getPaintings (@PathVariable Long id) {

        // Check if the given store id exists
        Store store = storeRepository.findById(id).orElseThrow(() ->
                new StoreNotFoundException(id));

        return store.getPaintings();
    }

    // Delete all of the paintings of a store
    @DeleteMapping("/shops/{id}/pictures")
    void fire(@PathVariable Long id) {

        // We cannot delete the paintings from the painting repository if they're still associated with the store
        // To avoid this, we need to:
        // 1- Create a copy of the list of paintings to fire
        // 2- Empty the list from the store
        // 3- Empty the list from the repository
        // If we didn't want to delete them also from painting repository, just from store stock, it's enough to run
        // the commands that start with store.Repository.

        List<Painting> paintings = new ArrayList<>(storeRepository.getOne(id).getPaintings());
        storeRepository.getOne(id).getPaintings().clear();
        // Needed to persist the changes
        storeRepository.flush();
        fireRepository(paintings);

    }

    // Delete list of paintings from painting repository
    private void fireRepository(List<Painting> paintings) {
        for (Painting p :
                paintings) {
            paintingRepository.delete(p);
        }
    }
}
