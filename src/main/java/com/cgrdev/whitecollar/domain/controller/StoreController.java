package com.cgrdev.whitecollar.domain.controller;

import com.cgrdev.whitecollar.domain.data.Store;
import com.cgrdev.whitecollar.domain.data.StoreRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController {

    private final StoreRepository storeRepository;

    StoreController (StoreRepository storeRepository) {
        this.storeRepository=storeRepository;
    }

    // Aggregate root

    @GetMapping("/shops/")
    List<Store> all() {
        return storeRepository.findAll();
    }

    @PostMapping("/shops/")
    Store newStore (@RequestBody Store newStore) {
        return storeRepository.save(newStore);
    }
}
