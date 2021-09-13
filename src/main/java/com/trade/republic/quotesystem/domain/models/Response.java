package com.trade.republic.quotesystem.domain.models;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Response {
    private JsonObject data;
    private String type;
}
