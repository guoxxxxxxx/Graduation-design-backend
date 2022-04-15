package com.hebust.entity.study;

import com.hebust.config.ParamsConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyVO {

    public static final StudyVO SUCCESS = new StudyVO(200, "success", null);
    public static final StudyVO FAIL = new StudyVO(400, "fail", null);

    private int status;
    private String message;
    private Object object;

}
