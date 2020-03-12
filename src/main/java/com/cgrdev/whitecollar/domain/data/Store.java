package com.cgrdev.whitecollar.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
// We don't want the paintings list in the JSON response
@JsonIgnoreProperties(value = { "paintings", "id" })
public class Store {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int capacity;
    // EYE!!! If you define the type with the abstract class (List), it cannot run correctly.
    // It seem that it needs an instantiable type.
//    private ArrayList<Painting> paintings;

    // Test: include a PaintingRepository to store the paintings, not a List
    //PaintingRepository paintingRepository;
    // TODO check java.util.Set
    @OneToMany()
    private List<Painting> paintings;

    Store() {}

    Store (String name, int capacity) {
        this.name=name;
        this.capacity = capacity;
        this.paintings = new ArrayList<>(capacity);
    }

    Store (String name, int capacity, List<Painting> paintings) {
        this.name=name;
        this.capacity = capacity;
        this.paintings = paintings;
    }

}

    /*public void addPainting(Painting painting) {
        paintings.add(painting);
    }*/
