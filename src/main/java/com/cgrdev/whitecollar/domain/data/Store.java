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
// By contract, we don't want the paintings list neither the id in the JSON response, just name and capacity
@JsonIgnoreProperties(value = { "paintings", "id" })
public class Store {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int capacity;
    // EYE!!! If you define the type with the abstract class (List), it cannot run correctly.
    // It seems that it needs an instantiable type.
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