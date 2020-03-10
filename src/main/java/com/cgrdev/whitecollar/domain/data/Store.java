package com.cgrdev.whitecollar.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Data
@Entity
// We don't want the paintings list in the JSON response
@JsonIgnoreProperties(value = { "paintings", "id" })
public class Store {

    // We tell name is the PK
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int capacity;
    // EYE!!! If you define the type with the abstract class (List), it cannot run correctly.
    // It seem that it needs an instantiable type.
    private ArrayList<Painting> paintings;

    Store() {}

    Store (String name, int capacity) {
        this.name=name;
        this.capacity = capacity;
        this.paintings = new ArrayList<>(capacity);
    }

    public void addPainting(Painting painting) {
        paintings.add(painting);
    }
}
