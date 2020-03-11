package com.cgrdev.whitecollar.domain.controller;

import com.cgrdev.whitecollar.domain.data.Painting;
import com.cgrdev.whitecollar.domain.data.PaintingRepository;
import com.cgrdev.whitecollar.domain.data.Store;
import com.cgrdev.whitecollar.domain.data.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
    boolean addPainting(@RequestBody Painting newPainting, @PathVariable Long id) {

        // TODO: treat case with non-existing store id
//        storeRepository.getOne(id).addPainting(paintingRepository.save(newPainting));
        newPainting.setEntryDate(new Date());
        log.info("Painting received: " + newPainting);
        log.info("Id received: " + id);

        return storeRepository.getOne(id).getPaintings().add(paintingRepository.save(newPainting));
    }

    // List paintings of a store
    // TODO: check correct id
    @GetMapping("/shops/{id}/pictures")
    List<Painting> getPaintings (@PathVariable Long id) {
        /*log.info("Store retrieved: " + storeRepository.getOne(id).getName());
        log.info("Store paintings: " +storeRepository.getOne(id).getPaintings().toString());*/
        return storeRepository.getOne(id).getPaintings();
    }
}
