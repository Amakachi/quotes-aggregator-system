package com.trade.republic.quotesystem.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


@Getter
@Setter
@ToString
@Entity
@Table(name = "instruments")
public class Instrument extends BaseModel {
    private String description;
    private String isin;
    @Transient
    private Double firstPrice;
    @Transient
    private Double lastPrice;
    private Double percentagePriceChange;

    public Instrument() {
    }

    public Instrument(String description, String isin, Double firstPrice, Double lastPrice) {
        this.description = description;
        this.isin = isin;
        this.firstPrice = firstPrice;
        this.lastPrice = lastPrice;
    }
}
