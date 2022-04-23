package com.hebust.service;

import com.hebust.entity.other.Percentage;

public interface IndexService {

    /**
     * 查询未被接单, 已被接单, 已完成项目所占百分比
     */
    Percentage queryPercentage();
}
