package com.trade.republic.quotesystem.enums;

import lombok.Getter;

@Getter
public enum Status {
    SUCCESS("success"),
    FAILED("failed");

    private String name;

     Status(String name){
        this.name = name;
    }
}
