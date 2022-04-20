package com.hebust.entity.trade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeVO {

    public static final TradeVO SUCCESS = new TradeVO(200, "success", null);
    public static final TradeVO FAIL = new TradeVO(400, "fail", null);

    private int status;
    private String message;
    private Object object;

    public static TradeVO SUCCESS(Object object){
        return new TradeVO(200, "success", object);
    }

    public static TradeVO FAIL(Object object){
        return new TradeVO(400, "fail", object);
    }
}
