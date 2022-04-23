package com.hebust.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexVO {
    private int status;
    private String message;
    private Object object;

    public static IndexVO SUCCESS(Object object){
        return new IndexVO(200, "success", object);
    }
}
