package com.cgrdev.whitecollar.domain.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Store {

    // We tell name is the PK
    @Id
    private String name;
    // EYE!!! If you define the type with the abstract class (List), it cannot run correctly.
    // It seem that it needs an instantiable type.
    private ArrayList<Painting> paintings;

    Store() {}

    Store (String name, int maxPaintings) {
        this.name=name;
        this.paintings = new ArrayList<>(maxPaintings);
    }

}
