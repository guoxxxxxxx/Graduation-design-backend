package com.hebust.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerVO {
    private int status;
    private String message;
    private Object object;

    public static ManagerVO SUCCESS(Object object){
        return new ManagerVO(200, "success", object);
    }
}
