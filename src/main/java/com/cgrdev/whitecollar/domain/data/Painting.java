package com.cgrdev.whitecollar.domain.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Painting {

    private static final String ANONYMOUS_PAINTER="Anonymous";

    // TODO: for the moment, the painter cannot be null
    //@Nullable
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
