package com.hebust.entity.errand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrandVO {

    private int status;
    private String message;
    private Object object;
}
