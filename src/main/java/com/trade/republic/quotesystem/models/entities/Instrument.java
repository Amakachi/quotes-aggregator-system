package com.trade.republic.quotesystem.models.entities;

import lombok.*;
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
