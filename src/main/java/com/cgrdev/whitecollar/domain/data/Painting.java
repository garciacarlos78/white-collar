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
    // We tell name is the PK
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

    Painting (String name, double price, Date entryDate) {
        this.painter = ANONYMOUS_PAINTER;
        this.name = name;
        this.price = price;
        this.entryDate = entryDate;
    }

    Painting (String name, String painter, double price) {
        this.painter = painter;
        this.name = name;
        this.price = price;
        this.entryDate = new Date();
    }

    // Constructor to add a painting just with name and painter name
    Painting (String name, String painter) {
        this.name = name;
        this.painter = painter;
        // The price is mandatory, we fix it to 25€ if it isn't given
        this.price=25;
        this.entryDate=new Date();
    }

    // Constructor to add a painting just with name (painter can be null)
    Painting (String name) {
        this.name = name;
        this.painter = ANONYMOUS_PAINTER;
        // The price is mandatory, we fix it to 25€ if it isn't given
        this.price=25;
        this.entryDate=new Date();
    }
}
