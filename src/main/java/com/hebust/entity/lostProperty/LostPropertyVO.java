package com.hebust.entity.lostProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LostPropertyVO {

    public static final LostPropertyVO SUCCESS = new LostPropertyVO(200, "success", null);
    public static final LostPropertyVO FAIL = new LostPropertyVO(400, "fail", null);

    private int status;
    private String message;
    private Object object;

    public static LostPropertyVO SUCCESS(Object o){
        return new LostPropertyVO(200,"success", o);
    }
}
