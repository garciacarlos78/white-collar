package com.cgrdev.whitecollar.domain.data;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Painting {

    @Nullable
    private String painter;
    // We tell name is the PK
    @Id
    private String name;
    private double price;
    private Date entryDate;

    Painting() {}

    Painting (String painter, String name, double price, Date entryDate) {
        this.painter = painter;
        this.name = name;
        this.price = price;
        this.entryDate = entryDate;
    }
}
