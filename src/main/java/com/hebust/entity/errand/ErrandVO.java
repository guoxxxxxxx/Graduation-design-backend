package com.hebust.entity.errand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrandVO {

    public static final ErrandVO SUCCESS = new ErrandVO(200, "success", null);
    public static final ErrandVO FAIL = new ErrandVO(400, "fail", null);

    private int status;
    private String message;
    private Object object;
}
