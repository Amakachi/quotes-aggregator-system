package com.trade.republic.quotesystem.models.entities;

import com.trade.republic.quotesystem.models.entities.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name ="quotes")
public class Quote extends BaseModel {
    private Double price;
    private String isin;


}
