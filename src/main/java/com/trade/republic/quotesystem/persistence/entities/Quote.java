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
@Table(name ="quotes")
public class Quote extends BaseModel {
    private Double price;
    private String isin;
}
