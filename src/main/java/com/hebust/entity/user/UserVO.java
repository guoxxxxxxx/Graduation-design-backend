package com.hebust.entity.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private int status;  //返回状态码
    private String massage; //返回消息
    private Object object; //返回的对象
}
