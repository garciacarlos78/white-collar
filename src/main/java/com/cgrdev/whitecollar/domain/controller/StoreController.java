package com.cgrdev.whitecollar.domain.controller;

import com.cgrdev.whitecollar.domain.data.Painting;
import com.cgrdev.whitecollar.domain.data.PaintingRepository;
import com.cgrdev.whitecollar.domain.data.Store;
import com.cgrdev.whitecollar.domain.data.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
// TODO: Is it necessary to have a PaintingController?
@RestController
public class StoreController {

    private final StoreRepository storeRepository;
    private final PaintingRepository paintingRepository;

    StoreController(StoreRepository storeRepository, PaintingRepository paintingRepository) {
        this.storeRepository=storeRepository;
        this.paintingRepository = paintingRepository;
    }

    // Aggregate root

    // Get stores list
    // Returned JSON list: {"name", "}
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

        // TODO: treat case with non-existing store id
        newPainting.setEntryDate(new Date());
        newPainting.setPrice(50);

        //log.info("Painting repository after insertion: " + paintingRepository.findAll().toString());

        //paintingRepository.save(newPainting);

        log.info("Store repository before insertion: " + storeRepository.getOne(id).getPaintings().toString());
        storeRepository.getOne(id).getPaintings().add(paintingRepository.save(newPainting));
        log.info("Store repository after insertion: " + storeRepository.getOne(id).getPaintings().toString());

        // Necessary to persist the changes
        storeRepository.flush();
    }

    // List paintings of a store
    // TODO: check correct id
    @GetMapping("/shops/{id}/pictures")
    List<Painting> getPaintings (@PathVariable Long id) {
        /*log.info("Store retrieved: " + storeRepository.getOne(id).getName());
        log.info("Store paintings: " +storeRepository.getOne(id).getPaintings().toString());*/
        return storeRepository.getOne(id).getPaintings();
    }

    // Delete all of the paintings of a store
    @DeleteMapping("/shops/{id}/pictures")
    void fire(@PathVariable Long id) {

        log.info(("Painting repository size before deletion: " + paintingRepository.count()));
        log.info(("Store list size before deletion: " + storeRepository.getOne(id).getPaintings().size()));

        storeRepository.getOne(id).getPaintings().clear();
        storeRepository.flush();
        // TODO: probably they still are on the paintings repository

        log.info(("Store list size before deletion: " + storeRepository.getOne(id).getPaintings().size()));
        log.info(("Painting repository size after deletion: " + paintingRepository.count()));

    }
}
