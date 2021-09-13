package com.trade.republic.quotesystem.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@ToString
@Entity
@Table(name = "instruments")
public class Instrument extends BaseModel {
    private String description;
    private String isin;
}
