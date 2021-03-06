package com.cgrdev.whitecollar.domain.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Painting {

    public static final String ANONYMOUS_PAINTER="Anonymous";

    private String painter;
    // Indicate name as PK
    @Id
    private String name;
    private double price;
    private Date entryDate;

    Painting() {}

    Painting (String name, String painter, double price, Date entryDate) {
        this.painter = painter;
        this.name = name;
        this.price = price;
        this.entryDate = entryDate;
    }

    // If we don't provide a painter name the painter name is anonymous
    Painting (String name, double price, Date entryDate) {
        this.painter = ANONYMOUS_PAINTER;
        this.name = name;
        this.price = price;
        this.entryDate = entryDate;
    }
}
