package com.hebust.entity.alumni;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlumniVO {

    private int status;
    private String message;
    private Object object;

    public static final AlumniVO SUCCESS = new AlumniVO(200, "success", null);
    public static final AlumniVO FAIL = new AlumniVO(400, "fail", null);

    public static AlumniVO SUCCESS(Object object){
        return new AlumniVO(200, "success", object);
    }

}
